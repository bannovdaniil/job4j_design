package ru.job4j.iterator.listiterator;

import org.hamcrest.core.Is;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class ListUtilsTest {

    @Test
    public void whenAddBefore() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 1, 2);
        assertThat(input, is(Arrays.asList(1, 2, 3)));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenAddBeforeWithInvalidIndex() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 3, 2);
    }

    @Test
    public void whenAddAfterLast() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2));
        ListUtils.addAfter(input, 2, 3);
        assertThat(input, is(Arrays.asList(0, 1, 2, 3)));
    }

    @Test
    public void whenRemoveIf() {
        List<Integer> input = new ArrayList<>(Arrays.asList(2, 0, 1, 2, 2, 3, 2));
        ListUtils.removeIf(input, n -> n == 2);
        assertThat(input, is(Arrays.asList(0, 1, 3)));
    }

    @Test
    public void whenReplaceIf() {
        List<Integer> input = new ArrayList<>(Arrays.asList(2, 0, 1, 2, 2, 3, 2));
        ListUtils.replaceIf(input, n -> n == 2, 5);
        assertThat(input, is(Arrays.asList(5, 0, 1, 5, 5, 3, 5)));
    }

    @Test
    public void whenRemoveAll() {
        List<Integer> input = new ArrayList<>(Arrays.asList(2, 0, 1, 2, 3, 2, 4));
        List<Integer> delete = new ArrayList<>(Arrays.asList(2, 3));
        ListUtils.removeAll(input, delete);
        assertThat(input, is(Arrays.asList(0, 1, 4)));
    }
}