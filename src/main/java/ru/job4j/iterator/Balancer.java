package ru.job4j.iterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * В этом задании надо реализовать балансир. Это класс, который равномерно распределяет данные из итератора по переданным ему спискам.
 */
public class Balancer {
    /**
     * 1. Идем последовательно по каждому элементу из списка @source
     * 2. Распределяем в каждый из элементов списка nodes последовательно перебирая индекс.
     */
    public static void split(List<ArrayList<Integer>> nodes, Iterator<Integer> source) {
        int index = 0;
        while (source.hasNext()) {
            nodes.get(index++).add(source.next());
            if (index >= nodes.size()) {
                index = 0;
            }
        }
    }
}