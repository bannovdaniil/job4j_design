package ru.job4j.tree;

import java.util.*;

public class SimpleTree<E> implements Tree<E> {
    private final Node<E> root;

    public SimpleTree(final E root) {
        this.root = new Node<>(root);
    }

    /**
     * Метод add - Должен
     * 1. находить узел по значению parent и
     * 2. добавлять в него дочерний узел со значением child.
     * В этом методе нужно проверить, что значения child еще
     * нет в дереве а parent есть.
     * 3. Если child есть, то метод должен вернуть false.
     *
     * @param parent - узел
     * @param child  - ветка
     * @return - удалось или нет
     */
    @Override
    public boolean add(E parent, E child) {
        boolean rsl = false;
        Optional<Node<E>> parentNode = findBy(parent);
        Optional<Node<E>> checkChildNode = findBy(child);
        if (parentNode.isPresent() && checkChildNode.isEmpty()) {
            Node<E> childNode = new Node<>(child);
            parentNode.get().children.add(childNode);
            rsl = true;
        }
        return rsl;
    }

    @Override
    public Optional<Node<E>> findBy(E value) {
        Optional<Node<E>> rsl = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (el.value.equals(value)) {
                rsl = Optional.of(el);
                break;
            }
            data.addAll(el.children);
        }
        return rsl;
    }
}