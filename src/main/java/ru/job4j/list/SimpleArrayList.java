package ru.job4j.list;

import java.util.*;

public class SimpleArrayList<T> implements List<T> {
    private T[] container;
    private int size;
    private int modCount;

    public SimpleArrayList(int capacity) {
        this.container = (T[]) new Object[capacity];
        this.modCount = 0;
    }

    @Override
    public void add(T value) {
        if (size == container.length) {
            int capacity = size * 2;
            T[] newContainer = (T[]) new Object[capacity];
            newContainer = Arrays.copyOf(container, capacity);
            container = newContainer;
        }
        container[size++] = value;
        this.modCount++;
    }

    @Override
    public T set(int index, T newValue) {
        Objects.checkIndex(index, container.length);
        T result = get(index);
        container[index] = newValue;
        return result;
    }

    @Override
    public T remove(int index) {
        Objects.checkIndex(index, container.length);
        T result = get(index);
        System.arraycopy(container, index + 1,
                container, index, container.length - (index + 1));
        size--;
        container[size] = null;
        this.modCount--;
        return result;
    }

    @Override
    public T get(int index) {
        Objects.checkIndex(index, container.length);
        return container[index];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        modCount = 0;
        return new Iterator<T>() {
            private int count = 0;

            @Override
            public boolean hasNext() {
                return count < size;
            }

            @Override
            public T next() {
                if (modCount != 0) {
                    throw new ConcurrentModificationException();
                }
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return container[count++];
            }
        };
    }
}

