package ru.job4j.map;

import org.hamcrest.core.Is;
import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SimpleMapTest {
    SimpleMap<String, String> map;

    @Before
    public void initData() {
        map = new SimpleMap<>();
        map.put("0", "text0");
        map.put("1", "text1");
        map.put("2", "text2");
        map.put("3", "text3");
        map.put("4", "text4");
    }

    @Test
    public void whenPutThen() {
        assertTrue(map.put("5", "text5"));
    }

    /**
     * доступ к capacity не имеем поэтому чисто интуитивно :)
     * на 6 элементе происходит расширение при 0.75
     */
    @Test
    public void whenPutAndExpandThen() {
        map.put("5", "text5");
        map.put("6", "text6");
        assertThat(map.get("6"), is("text6"));
    }

    @Test
    public void whenPutDoubleThen() {
        map.put("5", "text5");
        assertFalse(map.put("5", "text5"));
    }

    @Test
    public void whenGet() {
        map.put("5", "text5");
        assertThat(map.get("5"), is("text5"));
    }

    @Test
    public void whenGetNoKeyThenNull() {
        assertNull(map.get("5"));
    }

    @Test
    public void whenRemove() {
        map.put("5", "text5");
        map.remove("5");
        assertNull(map.get("5"));
    }

    @Test
    public void whenNoElementRemoveFalse() {
        assertFalse(map.remove("noKey"));
    }

    @Test
    public void whenExitsElementRemoveTrue() {
        map.put("5", "text5");
        assertTrue(map.remove("5"));
    }

    @Test
    public void whenNullIteratorThenFalse() {
        SimpleMap<String, String> map = new SimpleMap<>();
        var it = map.iterator();
        assertFalse(it.hasNext());
    }

    @Test
    public void whenNoElementIteratorThenFalse() {
        SimpleMap<String, String> map = new SimpleMap<>();
        var it = map.iterator();
        assertFalse(it.hasNext());
    }

    @Test
    public void whenAddIteratorHasNextTrue() {
        SimpleMap<String, String> map = new SimpleMap<>();
        map.put("5", "text5");
        var it = map.iterator();
        assertTrue(it.hasNext());
    }

    @Test
    public void whenAddIteratorMultiHasNextTrue() {
        SimpleMap<String, String> map = new SimpleMap<>();
        map.put("5", "text5");
        var it = map.iterator();
        assertTrue(it.hasNext());
        assertTrue(it.hasNext());
    }

    @Test
    public void whenAddIteratorNextOne() {
        SimpleMap<String, String> map = new SimpleMap<>();
        map.put("1", "text1");
        var it = map.iterator();
        assertThat(it.next(), Is.is("1"));
    }

    @Test
    public void whenAddIteratorNextOneNextTwo() {
        SimpleMap<String, String> map = new SimpleMap<>();
        map.put("1", "text1");
        map.put("2", "text1");
        var it = map.iterator();
        assertThat(it.next(), Is.is("1"));
        assertThat(it.next(), Is.is("2"));
    }

    @Test
    public void whenGetIteratorTwiceThenEveryFromBegin() {
        SimpleMap<String, String> map = new SimpleMap<>();
        map.put("1", "text1");
        map.put("2", "text1");
        var first = map.iterator();
        assertThat(first.hasNext(), Is.is(true));
        assertThat(first.next(), Is.is("1"));
        assertThat(first.hasNext(), Is.is(true));
        assertThat(first.next(), Is.is("2"));
        assertThat(first.hasNext(), Is.is(false));
        var second = map.iterator();
        assertThat(second.hasNext(), Is.is(true));
        assertThat(second.next(), Is.is("1"));
        assertThat(second.hasNext(), Is.is(true));
        assertThat(second.next(), Is.is("2"));
        assertThat(second.hasNext(), Is.is(false));
    }
}