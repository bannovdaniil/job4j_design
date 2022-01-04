package ru.job4j.list;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.stream.IntStream;

public class SimpleArrayListTest {
    List<Integer> list;

    @Before
    public void initData() {
        list = new SimpleArrayList<>(3);
        list.add(1);
        list.add(2);
        list.add(3);
    }

    @Test
    public void whenAddThenSizeIncrease() {
        Assert.assertEquals(3, list.size());
    }

    @Test
    public void whenAddAndGetByCorrectIndex() {
        Assert.assertEquals(Integer.valueOf(1), list.get(0));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenAddAndGetByIncorrectIndexThenGetException() {
        list.get(5);
    }

    @Test
    public void whenRemoveThenGetValueAndSizeDecrease() {
        Assert.assertEquals(3, list.size());
        Assert.assertEquals(Integer.valueOf(2), list.remove(1));
        Assert.assertEquals(2, list.size());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenRemoveByIncorrectIndexThenGetException() {
        list.remove(5);
    }

    @Test
    public void whenRemoveThenMustNotBeEmpty() {
        list.remove(1);
        Assert.assertEquals(Integer.valueOf(1), list.get(0));
        Assert.assertEquals(Integer.valueOf(3), list.get(1));
    }

    @Test
    public void whenAddNullThenMustBeSameBehavior() {
        list = new SimpleArrayList<>(3);
        list.add(null);
        list.add(null);
        Assert.assertEquals(2, list.size());
        Assert.assertNull(list.get(0));
        Assert.assertNull(list.get(1));
    }

    @Test
    public void whenSetThenGetOldValueAndSizeNotChanged() {
        Assert.assertEquals(Integer.valueOf(2), list.set(1, 22));
        Assert.assertEquals(3, list.size());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenSetByIncorrectIndexThenGetException() {
        list.set(5, 22);
    }

    @Test
    public void whenGetIteratorFromEmptyListThenHasNextReturnFalse() {
        list = new SimpleArrayList<>(5);
        Assert.assertFalse(list.iterator().hasNext());
    }

    @Test(expected = NoSuchElementException.class)
    public void whenGetIteratorFromEmptyListThenNextThrowException() {
        list = new SimpleArrayList<>(5);
        list.iterator().next();
    }

    @Test
    public void whenGetIteratorTwiceThenStartAlwaysFromBeginning() {
        Assert.assertEquals(Integer.valueOf(1), list.iterator().next());
        Assert.assertEquals(Integer.valueOf(1), list.iterator().next());
    }

    @Test
    public void whenCheckIterator() {
        Iterator<Integer> iterator = list.iterator();
        Assert.assertTrue(iterator.hasNext());
        Assert.assertEquals(Integer.valueOf(1), iterator.next());
        Assert.assertTrue(iterator.hasNext());
        Assert.assertEquals(Integer.valueOf(2), iterator.next());
        Assert.assertTrue(iterator.hasNext());
        Assert.assertEquals(Integer.valueOf(3), iterator.next());
        Assert.assertFalse(iterator.hasNext());
    }

    @Test
    public void whenNoPlaceThenMustIncreaseCapacity() {
        IntStream.range(3, 10).forEach(v -> list.add(v));
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenAddAfterGetIteratorThenMustBeException() {
        Iterator<Integer> iterator = list.iterator();
        list.add(4);
        iterator.next();
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenRemoveAfterGetIteratorThenMustBeException() {
        Iterator<Integer> iterator = list.iterator();
        list.add(0);
        iterator.next();
    }

}
