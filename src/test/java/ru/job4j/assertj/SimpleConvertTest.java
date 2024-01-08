package ru.job4j.assertj;

import org.assertj.core.data.Index;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class SimpleConvertTest {
    @Test
    void checkArray() {
        SimpleConvert simpleConvert = new SimpleConvert();
        String[] array = simpleConvert.toArray("first", "second", "three", "four", "five");
        assertThat(array).hasSize(5)
                .isNotNull()
                .contains("second")
                .contains("first", Index.atIndex(0))
                .containsAnyOf("zero", "second", "six")
                .doesNotContain("first", Index.atIndex(1));
    }

    @Test
    void toList() {
        SimpleConvert simpleConvert = new SimpleConvert();
        String[] elements = {"first", "second", "three", "four", "five", "five"};
        List<String> list = simpleConvert.toList(elements);

        int expectedCount = elements.length;
        String expectedFirst = elements[0];
        String expectedLast = elements[elements.length - 1];


        assertThat(list).first().isEqualTo(expectedFirst);
        assertThat(list).last().isEqualTo(expectedLast);

        assertThat(list)
                .isNotNull()
                .hasSize(expectedCount)
                .contains(expectedFirst, Index.atIndex(0))
                .contains(expectedLast);
    }

    @Test
    void toSet() {
        SimpleConvert simpleConvert = new SimpleConvert();
        String[] elements = {"first", "second", "three", "four", "five", "first", "five"};
        Set<String> set = simpleConvert.toSet(elements);

        int expectedCount = set.size();

        assertThat(set)
                .isNotNull()
                .containsOnlyOnce("first")
                .containsOnlyOnce("five")
                .hasSize(expectedCount)
                .contains("first")
                .allSatisfy(e -> {
                    assertThat(e.length()).isGreaterThan(3);
                });
    }

    @Test
    void toMap() {
        SimpleConvert simpleConvert = new SimpleConvert();
        String[] elements = {"first", "second", "three", "four", "five", "first", "five"};
        Map<String, Integer> map = simpleConvert.toMap(elements);

        int expectedCount = map.size();

        assertThat(map)
                .isNotNull()
                .hasSize(expectedCount)
                .containsKeys("first", "second", "five")
                .containsValues(4, 1, 2)
                .doesNotContainKey("zero")
                .doesNotContainValue(10);
    }
}