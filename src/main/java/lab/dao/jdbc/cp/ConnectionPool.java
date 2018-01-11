package lab.dao.jdbc.cp;

import lab.common.Pool;
import lab.common.PropertyMatcher;
import lab.common.function.Exceptional;
import lab.common.function.ExceptionalBiFunction;
import lombok.SneakyThrows;
import lombok.val;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

@FunctionalInterface
public interface ConnectionPool extends JdbcDao {

    String DEFAULT_DB_PROPERTIES_FILE_NAME = "db.properties";
    String SQL_FILE_NAME_SUFFIX = ".sql";
    String JDBC_DRIVER_CLASS_KEY = "driver";
    String JDBC_URL_KEY = "url";
    String JDBC_USER_KEY = "user";
    String JDBC_PASSWORD_KEY = "password";
    String JDBC_CONNECTION_POOL_SIZE_KEY = "poolSize";
    String JDBC_INIT_SCRIPTS_FOLDER_KEY = "initScriptsPath";

    static ConnectionPool create(String rootFilePath) {

        String inputStreamSupplier = rootFilePath + DEFAULT_DB_PROPERTIES_FILE_NAME;

        //noinspection ConstantConditions
        return PropertyMatcher.from(inputStreamSupplier)
                .ensureKeysExist(
                        JDBC_DRIVER_CLASS_KEY,
                        JDBC_URL_KEY,
                        JDBC_INIT_SCRIPTS_FOLDER_KEY,
                        JDBC_USER_KEY,
                        JDBC_PASSWORD_KEY)
                .with(JDBC_DRIVER_CLASS_KEY, driverClassName -> Class.forName(driverClassName.get()))
                .map(JDBC_URL_KEY, (url, pm) ->
                        pm.mapInt(JDBC_CONNECTION_POOL_SIZE_KEY, 5, size ->
                                pm.map(JDBC_INIT_SCRIPTS_FOLDER_KEY, scriptsFolder ->
                                        ConnectionPool.create(size, url.get(), pm.get())
                                                .executeScripts(rootFilePath + scriptsFolder))));
    }

    static ConnectionPool create(int size, String jdbcUrl, Properties properties) {
        assert properties.containsKey(JDBC_USER_KEY);
        assert properties.containsKey(JDBC_PASSWORD_KEY);
        assert properties.size() == 2;
        return new Pool<>(
                Connection.class,
                ExceptionalBiFunction.supplyUnchecked(
                        DriverManager::getConnection, jdbcUrl, properties),
                size
        )::get;
    }

    default ConnectionPool executeScripts(String dbFilesFolderPath) {

        if (dbFilesFolderPath == null)
            return this;

        if (!dbFilesFolderPath.endsWith("/"))
            dbFilesFolderPath += "/";

        List<Path> pathList = new ArrayList<>();
        Path path;
        for (int i = 0; (path = Paths.get(dbFilesFolderPath + ++i + SQL_FILE_NAME_SUFFIX)).toFile().exists(); )
            pathList.add(path);

        return executeScripts(pathList.toArray(new Path[0]));
    }

    @SneakyThrows
    default ConnectionPool executeScripts(Path... files) {
        for (Path file : files)
            execute(new String(Files.readAllBytes(file)));

        return this;
    }

    default <T> CompletableFuture<Exceptional<T, SQLException>> mapConnectionAsync(Function<Connection, T> connectionMapper) {
        return CompletableFuture.supplyAsync(() -> {
            try (val connection = get()) {
                return Exceptional.withValue(connectionMapper.apply(connection));
            } catch (SQLException e) {
                return Exceptional.withException(e);
            }
        });
    }
}
