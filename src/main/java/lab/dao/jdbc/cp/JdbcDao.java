package lab.dao.jdbc.cp;

import lab.common.CheckedConsumer;
import io.vavr.CheckedFunction1;
import lombok.SneakyThrows;
import lombok.val;

import java.sql.Connection;
import java.util.function.Supplier;

@FunctionalInterface
public interface JdbcDao extends Supplier<Connection> {

    @SneakyThrows
    default <T> T mapConnection(CheckedFunction1<Connection, T> connectionMapper) {
        try (val con = get()) {
            return connectionMapper.unchecked().apply(con);
        }
    }

    @SneakyThrows
    default void withConnection(CheckedConsumer<Connection> connectionConsumer) {
        try (val con = get()) {
            connectionConsumer.accept(con);
        }
    }
}
