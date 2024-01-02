package ru.job4j.iterator;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.Consumer;

public class CyclicIterator<T> implements Iterator<T> {

    private final List<T> data;
    private int index = 0;

    public CyclicIterator(List<T> data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        if (index == data.size()) {
            index = 0;
        }
        return index < data.size();
    }

    @Override
    public T next() {
        if (data.isEmpty()) {
            throw new NoSuchElementException();
        }
        return data.get(index++);
    }

    @Override
    public void remove() {
        data.remove(index);
        if (index >= data.size()) {
            index = 0;
        }
    }

    @Override
    public void forEachRemaining(Consumer<? super T> action) {
        throw new UnsupportedOperationException();
    }
}