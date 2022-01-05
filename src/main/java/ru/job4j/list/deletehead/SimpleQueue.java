package ru.job4j.list.deletehead;

import java.util.NoSuchElementException;

public class SimpleQueue<T> {
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();
    private int count = 0;

    /**
     * и так что мы можем получить не дополняя никаких еще либо переменных
     * в сам класс. Только прерывание в пустом списке!
     * 1. элемент перед прерыванием будет последним и должен стать первым
     *
     * @return значение элемента
     */
    public T poll() {
        if (count == 0) {
            throw new NoSuchElementException();
        }
        change(out, in);
        count--;
        return out.pop();
    }

    public void push(T value) {
        change(in, out);
        in.push(value);
        count++;
    }

    private void change(SimpleStack<T> a1, SimpleStack<T> a2) {
        for (int i = 0; i < count; i++) {
            try {
                a1.push(a2.pop());
            } catch (NoSuchElementException err) {
                break;
            }
        }
    }
}