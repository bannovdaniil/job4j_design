package ru.job4j.list.linkedlist;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * element - хранимое значение
 * prev - прошлый элемент
 * last - следующий
 * end - конечный для быстрого добавления
 * begin - начало списка для Итератора.
 * size - размер списка
 * modCount - флаг модификации
 *
 * @param <E> - тип хранимых элементов
 */
public class SimpleLinkedList<E> implements List<E> {
    private Node<E> node;
    private Node<E> end;
    private int size = 0;
    private int modCount;

    public SimpleLinkedList() {
        this.node = new Node<E>();
        this.end = this.node;
        this.modCount = 0;
    }

    /**
     * 1. Пусть будет в рекурсии.
     * иначе придется бегать с помощью foreach
     * if (last == null) {
     * last = new SimpleLinkedList<>();
     * last.element = value;
     * last.prev = this;
     * } else {
     * last.add(value);
     * }
     * 2. можно еще попробывать хранить начало и хвост.
     * Ведь у пользователя всега в руках только первый элемент.
     * в таком случаи добавляем в первом prev = null
     * а последний указываем то, что создали.
     *
     * @param value - то что надо положить в список.
     */
    @Override
    public void add(E value) {
        if (size == 0) {
            this.node.element = value;
            size++;
            return;
        }
        end.next = new Node<E>();
        end.next.element = value;
        end.next.prev = end;
        end = end.next;
        this.size++;
        modCount++;
    }

    /**
     * 1. рекурсия... отменяется сдохнет на больших объемах.
     * 2. foreach...
     * 3. можно попробывать топать к элементу с разных сторон от куда
     * ближе или со старта или с конца
     *
     * @param index - порядковый номер
     * @return - элемент списка
     */
    @Override
    public E get(int index) {
        Objects.checkIndex(index, size);
        if (index == size - 1) {
            return end.element;
        }
        var el = node;
        if (index < size / 2) {
            for (int i = 1; i < index; i++) {
                if (node.next != null) {
                    el = node.next;
                }
            }
        } else {
            el = end;
            for (int i = size - 1; i != index; i--) {
                if (end.prev != null) {
                    el = end.prev;
                }
            }
        }
        return el.element;
    }

    private class Node<E> {
        private E element;
        private Node<E> prev;
        private Node<E> next;

    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private Node<E> current = node;
            private final int mod = modCount;
            private int inCount = 0;

            @Override
            public boolean hasNext() {
                if (modCount != mod) {
                    throw new ConcurrentModificationException();
                }
                return inCount != size;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                var res = current.element;
                current = current.next;
                inCount++;
                return res;
            }
        };
    }
}