package ru.job4j.list.deletehead;

import java.util.NoSuchElementException;

public class SimpleQueue<T> {
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();

    /**
     * и так что мы можем получить не дополняя никаких еще либо переменных
     * в сам класс. Только прерывание в пустом списке!
     * 1. элемент перед прерыванием будет последним и должен стать первым
     *
     * @return значение элемента
     */
    public T poll() {
        while (true) {
            try {
                out.push(in.pop());
            } catch (NoSuchElementException err) {
                break;
            }
        }

        T old = null;
        while (true) {
            try {
                if (old == null) {
                    old = out.pop();
                } else {
                    in.push(out.pop());
                }
            } catch (NoSuchElementException err) {
                if (old != null) {
                    out.push(old);
                }
                break;
            }
        }

        return out.pop();
    }

    public void push(T value) {
        in.push(value);
    }
}