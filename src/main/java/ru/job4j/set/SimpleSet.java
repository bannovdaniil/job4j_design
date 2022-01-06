package ru.job4j.set;

import ru.job4j.list.SimpleArrayList;

import java.util.Iterator;
import java.util.Objects;

public class SimpleSet<T> implements Set<T> {
    private SimpleArrayList<T> set = new SimpleArrayList<>();

    /**
     * должна быть проверка на дубль
     *
     * @param value - значение
     * @return - нет дублей true, false элемент уже есть в списке.
     */
    @Override
    public boolean add(T value) {
        var res = !this.contains(value);
        if (res) {
            set.add(value);
        }
        return res;
    }

    /**
     * в условии обозначено сравнивать через Objects.equals
     *
     * @param value - значение
     * @return true - нашли, false - не обнаружено
     */
    @Override
    public boolean contains(T value) {
        for (var t : set) {
            if (Objects.equals(t, value)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return set.iterator();
    }
}
