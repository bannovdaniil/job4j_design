package ru.job4j.iterator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class NonNullIteratorTest {
    private Iterator<Integer> iterator;

    @BeforeEach
    public void setUp() {
        iterator = new NonNullIterator(new Integer[]{null, null, 2, null, null, null, -4, null, 6, null});
    }

    @DisplayName("Первое число, в конце число")
    @Test
    void firstNumberAndLastNumber() {
        Iterator<Integer> iterator = new NonNullIterator(new Integer[]{10, null, null, null, -7, null, 6, 5});
        Assertions.assertTrue(iterator.hasNext());
        Assertions.assertEquals(10, iterator.next());
        Assertions.assertTrue(iterator.hasNext());
        Assertions.assertEquals(-7, iterator.next());
        Assertions.assertTrue(iterator.hasNext());
        Assertions.assertEquals(6, iterator.next());
        Assertions.assertTrue(iterator.hasNext());
        Assertions.assertEquals(5, iterator.next());
        Assertions.assertFalse(iterator.hasNext());
    }

    @DisplayName("Первое число, в конце null")
    @Test
    void firstNumberAndLastNull() {
        Iterator<Integer> iterator = new NonNullIterator(new Integer[]{10, null, null, null, -7, null, 6, null});
        Assertions.assertTrue(iterator.hasNext());
        Assertions.assertEquals(10, iterator.next());
        Assertions.assertTrue(iterator.hasNext());
        Assertions.assertEquals(-7, iterator.next());
        Assertions.assertTrue(iterator.hasNext());
        Assertions.assertEquals(6, iterator.next());
        Assertions.assertFalse(iterator.hasNext());
    }

    @DisplayName("Все элементы, в списке первый null, в конце null")
    @Test
    void shouldReturnNotNullElementsSequentially() {
        assertThat(iterator.hasNext()).isTrue();
        assertThat(iterator.next()).isEqualTo(2);
        assertThat(iterator.hasNext()).isTrue();
        assertThat(iterator.next()).isEqualTo(-4);
        assertThat(iterator.hasNext()).isTrue();
        assertThat(iterator.next()).isEqualTo(6);
        assertThat(iterator.hasNext()).isFalse();
        assertThatThrownBy(iterator::next)
                .isInstanceOf(NoSuchElementException.class);
    }

    @DisplayName("Дважды hasNext")
    @Test
    void sequentialHasNextInvocationDoesntAffectRetrievalOrder() {
        assertThat(iterator.hasNext()).isTrue();
        assertThat(iterator.hasNext()).isTrue();
        assertThat(iterator.next()).isEqualTo(2);
        assertThat(iterator.next()).isEqualTo(-4);
        assertThat(iterator.next()).isEqualTo(6);
    }

    @DisplayName("Список с null")
    @Test
    void shouldReturnFalseIfNoAnyNotNullElements() {
        iterator = new NonNullIterator(new Integer[]{null});
        assertThat(iterator.hasNext()).isFalse();
    }

    @DisplayName("Пустой список")
    @Test
    void shouldReturnFalseIfNoAnyElements() {
        iterator = new NonNullIterator(new Integer[]{});
        assertThat(iterator.hasNext()).isFalse();
    }

    @DisplayName("Список без null")
    @Test
    void allNumbersAreNotNull() {
        iterator = new NonNullIterator(new Integer[]{2, 4, 6, 8});
        assertThat(iterator.hasNext()).isTrue();
        assertThat(iterator.next()).isEqualTo(2);
        assertThat(iterator.hasNext()).isTrue();
        assertThat(iterator.next()).isEqualTo(4);
        assertThat(iterator.hasNext()).isTrue();
        assertThat(iterator.next()).isEqualTo(6);
        assertThat(iterator.hasNext()).isTrue();
        assertThat(iterator.next()).isEqualTo(8);
        assertThat(iterator.hasNext()).isFalse();
    }

    @DisplayName("Список из null")
    @Test
    void allNumbersAreNull() {
        iterator = new NonNullIterator(new Integer[]{null, null, null, null});
        assertThat(iterator.hasNext()).isFalse();
        assertThat(iterator.hasNext()).isFalse();
    }
}