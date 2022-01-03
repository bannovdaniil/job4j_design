package ru.job4j.generics.memstore;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public final class MemStore<T extends Base> implements Store<T> {

    private final Map<String, T> storage = new HashMap<>();

    /**
     * Реализация в Oracle...
     * V v = map.get(key);
     * if (v == null)
     * v = map.put(key, value);
     * return v;
     *
     * @param model - любой объект с ID
     */

    @Override
    public void add(T model) {
        storage.putIfAbsent(model.getId(), model);
    }

    /**
     * if (map.containsKey(key)) {
     * return map.put(key, value);
     * } else
     * return null;
     *
     * @param id    - id объекта
     * @param model - объект
     * @return - результат операции
     */
    @Override
    public boolean replace(String id, T model) {
        if (storage.containsKey(id)) {
            storage.put(id, model);
            return true;
        }
        return false;
    }

    /**
     * if (map.containsKey(key) && Objects.equals(map.get(key), value)) {
     * map.remove(key);
     * return true;
     * } else
     * return false;
     *
     * @param id - id объекта
     * @return - результат выполнения
     */
    @Override
    public boolean delete(String id) {
        if (storage.containsKey(id)) {
            storage.remove(id);
            return true;
        }
        return false;
    }

    /**
     * Найти объект по id
     * V get(Object key)
     * @param id - id объекта
     * @return - объект или null
     */
    @Override
    public T findById(String id) {
        if (storage.containsKey(id)) {
            return storage.get(id);
        }
        return null;
    }
}
