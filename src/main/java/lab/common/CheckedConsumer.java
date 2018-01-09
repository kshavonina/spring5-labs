package lab.common;

import java.util.function.Consumer;

@FunctionalInterface
public interface CheckedConsumer<T, E extends Throwable> extends Consumer<T> {

    void acceptThrows(T t) throws E;

    @Override
    default void accept(T t) {
        try {
            accept(t);
        } catch (Throwable throwable) {
            ifThrowable((E) throwable);
        }
    }

    default void ifThrowable(Throwable throwable) {
        throw new RuntimeException(throwable);
    }
}
