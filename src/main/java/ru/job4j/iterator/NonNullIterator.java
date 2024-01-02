package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Consumer;

public class NonNullIterator implements Iterator<Integer> {
    private final Integer[] data;
    private int index = 0;

    public NonNullIterator(Integer[] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        return getNextIndex(index) < data.length;
    }

    @Override
    public Integer next() {
        index = getNextIndex(index);
        if (index >= data.length) {
            throw new NoSuchElementException();
        }
        return data[index++];
    }

    private Integer getNextIndex(int tempIndex) {
        while (tempIndex < data.length && data[tempIndex] == null) {
            tempIndex++;
        }
        return tempIndex;
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void forEachRemaining(Consumer<? super Integer> action) {
        throw new UnsupportedOperationException();
    }
}
