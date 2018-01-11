package lab.common.function;

import java.util.function.Consumer;

@FunctionalInterface
public interface ExceptionalConsumer<T, E extends Throwable> extends Consumer<T> {

    static <T, E extends Exception> Consumer<T> toUncheckedConsumer(
            ExceptionalConsumer<T, E> exceptionalConsumer) {
        return exceptionalConsumer;
    }

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
