package ru.job4j.list.revert;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ForwardLinked<T> implements Iterable<T> {
    private Node<T> head;
    int size = 0;

    public void add(T value) {
        size++;
        Node<T> node = new Node<T>(value, null);
        if (head == null) {
            head = node;
            return;
        }
        Node<T> tail = head;
        while (tail.next != null) {
            tail = tail.next;
        }
        tail.next = node;
    }

    /**
     * Переходим на ресурсо емкое решение 3.
     * Меняем не ноды, а значения, тогда все просто...
     * Цикл на 1 проход меньше, так как внутри пользуем два элемента.
     * с каждым проходом, внутренний цикл на 1 меньше, т.к.
     * последний элемент нашел свое место.
     * По сути пузырьковый алгоритм.
     * Можно ли как-то его упростить?
     * 1. для больших массивов таскать пачками. :)
     * 2. иначе как вариант два потока с начала и из конца.
     * - т.к. в ноде только одно направление, как следствие нет.
     * 3. можно при первом проходе мутить ноду стек.
     * с обратным порядком и потом её забить в head.
     * - имеет смысл, только при большом дереве.
     *
     * @return - успех или нет
     */
    public boolean revert() {
        if (size < 2) {
            return false;
        }
        Node<T> rHead = null;
        var start = head;
        for (int i = 0; i < size; i++) {
            rHead = new Node<T>(start.value, rHead);
            start = start.next;
        }
        head = rHead;
        return true;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            Node<T> node = head;

            @Override
            public boolean hasNext() {
                return node != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T value = node.value;
                node = node.next;
                return value;
            }
        };
    }

    private static class Node<T> {
        T value;
        Node<T> next;

        public Node(T value, Node<T> next) {
            this.value = value;
            this.next = next;
        }
    }
}
