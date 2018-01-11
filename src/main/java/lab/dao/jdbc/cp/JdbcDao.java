package lab.dao.jdbc.cp;

import lab.common.function.ExceptionalConsumer;
import lab.common.function.ExceptionalFunction;
import lombok.SneakyThrows;
import lombok.val;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.function.Supplier;

@FunctionalInterface
public interface JdbcDao extends Supplier<Connection> {

    @SneakyThrows
    default <T> T mapConnection(ExceptionalFunction<Connection, T, SQLException> connectionMapper) {
        try (val con = get()) {
            return connectionMapper.map(con);
        }
    }

    @SneakyThrows
    default void withConnection(ExceptionalConsumer<Connection, SQLException> connectionConsumer) {
        try (val con = get()) {
            connectionConsumer.accept(con);
        }
    }

    default void withStatement(ExceptionalConsumer<Statement, SQLException> statementConsumer) {
        withConnection(connection -> {
            try (val statement = connection.createStatement()) {
                statementConsumer.accept(statement);
            }
        });
    }

    default void execute(String sql) {
        withStatement(statement -> statement.executeUpdate(sql));
    }
}
