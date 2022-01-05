package ru.job4j.list.deletehead;

import java.util.NoSuchElementException;

public class SimpleQueue<T> {
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();
    private int countIn = 0;
    private int countOut = 0;

    /**
     * и так что мы можем получить не дополняя никаких еще либо переменных
     * в сам класс. Только прерывание в пустом списке!
     * 1. элемент перед прерыванием будет последним и должен стать первым
     *
     * @return значение элемента
     */
    public T poll() {
        if (countIn == 0 && countOut == 0) {
            throw new NoSuchElementException();
        }
        if (countIn > 0) {
            countOut = change(out, in, countIn);
            countIn = 0;
        }
        countOut--;
        return out.pop();
    }

    public void push(T value) {
        if (countOut > 0) {
            countIn = change(in, out, countOut);
            countOut = 0;
        }
        in.push(value);
        countIn++;
    }

    private int change(SimpleStack<T> a1, SimpleStack<T> a2, int count) {
        for (int i = 0; i < count; i++) {
            a1.push(a2.pop());
        }
        return count;
    }
}