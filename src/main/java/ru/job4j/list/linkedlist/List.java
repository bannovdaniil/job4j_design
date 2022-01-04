package ru.job4j.list.linkedlist;

public interface List<E> extends Iterable<E> {
    void add(E value);

    E get(int index);
}
