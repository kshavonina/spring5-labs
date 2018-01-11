package lab.common;

import lab.common.function.ExceptionalConsumer;
import lab.common.function.ExceptionalSupplier;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.function.Function;
import java.util.function.Supplier;

import static lab.common.function.ExceptionalSupplier.avoid;

public class Pool<T extends AutoCloseable> implements Supplier<T>, AutoCloseable {

    private static final String CLOSE = "close";

    private Function<InvocationHandler<T>, T> proxyMaker;
    private BlockingQueue<T> freeObjectsQueue;
    private volatile boolean isClosing;

    public Pool(Class<T> anInterface, Supplier<T> generator, int size) {
        proxyMaker = InvocationHandler.getProxyMakerFor(anInterface);
        freeObjectsQueue = new ArrayBlockingQueue<>(size);

        for (int i = 0; i < size; i++)
            freeObjectsQueue.add(proxy(generator.get()));
    }

    private T proxy(T t) {
        return proxyMaker.apply(
                (proxy, method, chain, args) ->
                        method.getName().equals(CLOSE) && !isClosing ?
                                avoid(freeObjectsQueue.offer(proxy)) :
                                chain.apply(t));
    }

    @Override
    public T get() {
        if (isClosing)
            throw new RuntimeException("Trying to map object from closed pool!");
        return ExceptionalSupplier.getOrThrowUnchecked(freeObjectsQueue::take);
    }

    @Override
    public void close() {
        isClosing = true;
        freeObjectsQueue.forEach(ExceptionalConsumer.toUncheckedConsumer(T::close));
    }
}
