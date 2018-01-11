package lab.dao.jdbc.cp;

import lombok.SneakyThrows;

import java.sql.*;

public class HwJdbc implements JdbcDao {

    private static final String URL = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1";
    private static final String DRIVER = "org.h2.Driver";
    private static final String USER = "";
    private static final String PASSWORD = "";

    private static final String SQL_INIT = "CREATE TABLE Person (" +
            "  id         INT PRIMARY KEY AUTO_INCREMENT," +
            "  first_name VARCHAR(255) NOT NULL," +
            "  last_name  VARCHAR(255)," +
            "  permission BOOLEAN         DEFAULT FALSE," +
            "  dob        DATE," +
            "  email      VARCHAR(255) NOT NULL," +
            "  password   VARCHAR(255) NOT NULL," +
            "  address    VARCHAR(255)," +
            "  telephone  VARCHAR(15)" +
            ")";

    private static final String SQL_INSERT = "INSERT INTO Person (first_name, last_name, permission, dob, email, password, address, telephone)" +
            " VALUES ('Jose', 'Eglesias', TRUE, '1980-06-15', 'Jose_Eglesias@mail.es', 'qwerty', 'Franco squere, 5/1, 10'," +
            " '+38007654321')";

    private static final String SQL_SELECT = "SELECT * FROM Person WHERE id = 1";

    @SneakyThrows
    void printFirstName() {
        Class.forName(DRIVER);

        String firstName = mapConnection(connection -> {
            try (Statement statement = connection.createStatement()) {
                statement.executeUpdate(SQL_INIT);
                statement.executeUpdate(SQL_INSERT);
                try (ResultSet resultSet = statement.executeQuery(SQL_SELECT)) {
                    return !resultSet.next() ? null :
                            "first_name = " + resultSet.getString("first_name");
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });

        System.out.println(firstName);
    }

    @Override
    @SneakyThrows
    public Connection get() {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
