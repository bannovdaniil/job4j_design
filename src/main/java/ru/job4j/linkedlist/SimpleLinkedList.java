package ru.job4j.linkedlist;

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
    private E element;
    private SimpleLinkedList<E> prev;
    private SimpleLinkedList<E> last;
    private SimpleLinkedList<E> end;
    private SimpleLinkedList<E> begin;
    private int size = 0;
    private int modCount;

    public SimpleLinkedList() {
        this.begin = this;
        this.end = this;
        this.prev = null;
        this.last = null;
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
            this.element = value;
            size++;
            return;
        }
        end.last = new SimpleLinkedList<E>();
        end.last.element = value;
        end.last.prev = end;
        end = end.last;
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
        var el = this;
        for (int i = 1; i < index; i++) {
            if (el.last != null) {
                el = el.last;
            }
        }
        return el.element;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private SimpleLinkedList<E> current = begin;
            private final int mod = modCount;

            @Override
            public boolean hasNext() {
                return current != null && current.element != null;
            }

            @Override
            public E next() {
                if (modCount != mod) {
                    throw new ConcurrentModificationException();
                }
                var res = current.element;
                current = current.last;
                return res;
            }
        };
    }
}