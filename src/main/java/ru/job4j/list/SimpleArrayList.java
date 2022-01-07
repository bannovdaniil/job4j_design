package ru.job4j.list;

import java.util.*;

public class SimpleArrayList<T> implements List<T> {
    private T[] container;
    private int size;
    private int modCount;

    public SimpleArrayList() {
        this.container = (T[]) new Object[10];
        this.modCount = 0;
    }

    public SimpleArrayList(int capacity) {
        this.container = (T[]) new Object[capacity];
        this.modCount = 0;
    }

    private T[] grow() {
        int capacity = (size == 0 ? 1 : size) * 2;
        return Arrays.copyOf(container, capacity);
    }

    @Override
    public void add(T value) {
        if (size == container.length) {
            container = grow();
        }
        container[size++] = value;
        this.modCount++;
    }

    @Override
    public T set(int index, T newValue) {
        T result = get(index);
        container[index] = newValue;
        return result;
    }

    @Override
    public T remove(int index) {
        T result = get(index);
        System.arraycopy(container, index + 1,
                container, index, container.length - (index + 1));
        container[--size] = null;
        this.modCount++;
        return result;
    }

    @Override
    public T get(int index) {
        Objects.checkIndex(index, size);
        return container[index];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        int iteratorMod = modCount;
        return new Iterator<T>() {
            private int count = 0;

            @Override
            public boolean hasNext() {
                if (iteratorMod != modCount) {
                    throw new ConcurrentModificationException();
                }
                return count < size;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return container[count++];
            }
        };
    }
}

