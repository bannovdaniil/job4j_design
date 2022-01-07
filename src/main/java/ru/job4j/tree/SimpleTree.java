package ru.job4j.tree;

import ru.job4j.generics.Predator;

import java.util.*;
import java.util.function.Predicate;

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

    private Optional<Node<E>> checkTree(Predicate<Node<E>> filter) {
        Optional<Node<E>> result = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (filter.test(el)) {
                result = Optional.of(el);
                break;
            }
            data.addAll(el.children);
        }
        return result;
    }

    public boolean isBinary() {
        Optional<Node<E>> result = checkTree((n) -> n.children.size() > 2);
        return result.isEmpty();
    }

    @Override
    public Optional<Node<E>> findBy(E value) {
        return checkTree((n) -> n.value.equals(value));
    }
}