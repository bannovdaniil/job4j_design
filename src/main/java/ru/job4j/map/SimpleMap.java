package ru.job4j.map;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleMap<K, V> implements Map<K, V> {
    private static final float LOAD_FACTOR = 0.75f;
    private int capacity = 8;
    private int count = 0;
    private int modCount = 0;
    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        boolean res = false;
        if (count == capacity * LOAD_FACTOR) {
            expand();
        }
        int index = indexFor(key.hashCode());
        if (table[index] == null) {
            table[index] = new MapEntry<>(key, value);
            count++;
            modCount++;
            res = true;
        }
        return res;
    }

    private int hash(int hashCode) {
        return (hashCode) ^ (hashCode >>> 16);
    }

    private int indexFor(int hash) {
        return hash % capacity;
    }

    /**
     * для мульти поточного не подойдет т.к. indexFor работает только
     * с внутренней переменной capacity на время переделки ключей,
     * можно получить при доступе к map даже при чтении значений
     * исключение OutOfBound, на то время пока не закончится цикл.
     * Одно из решений добавить монитор или дать возможность
     * indexFor принимать еще одно значение в виде capacity.
     */
    private void expand() {
        int newCapacity = capacity * 2;
        MapEntry<K, V>[] tmp = new MapEntry[newCapacity];
        capacity = newCapacity;
        for (int i = 0; i < table.length; i++) {
            if (table[i] != null) {
                int newIndex = indexFor(hash(table[i].key.hashCode()));
                tmp[newIndex] = new MapEntry<>(table[i].key, table[i].value);
            }
        }
        table = tmp;
    }

    @Override
    public V get(K key) {
        int index = indexFor(key.hashCode());
        V res = null;
        if (table[index] != null) {
            if (Objects.equals(table[index].key, key)) {
                res = table[index].value;
            }
        }
        return res;
    }

    @Override
    public boolean remove(K key) {
        boolean res = false;
        int index = indexFor(key.hashCode());
        if (table[index] != null) {
            if (Objects.equals(table[index].key, key)) {
                table[index] = null;
                res = true;
            }
        }
        return res;
    }

    @Override
    public Iterator<K> iterator() {
        int iteratorMod = modCount;
        return new Iterator<>() {
            private int index = 0;

            @Override
            public boolean hasNext() {
                if (iteratorMod != modCount) {
                    throw new ConcurrentModificationException();
                }
                while (index < capacity && table[index] == null) {
                    index++;
                }
                return index < capacity;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return table[index++].key;
            }
        };
    }

    private static class MapEntry<K, V> {
        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

}

