package ru.job4j.assertj;

import org.assertj.core.api.Assertions;
import org.assertj.core.data.Percentage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;


class BoxTest {

    @DisplayName("whatsThis method")
    @ParameterizedTest
    @CsvSource({
            "0, 10, Sphere",
            "4, 6, Tetrahedron",
            "8, 12, Cube",
            "2, 5, 'Unknown object'"
    })
    void whatsThis(int vertex, int edge, String expected) {
        Box box = new Box(vertex, edge);
        String name = box.whatsThis();
        Assertions.assertThat(name).isEqualTo(expected);
    }

    @DisplayName("getNumberOfVertices method")
    @ParameterizedTest
    @CsvSource({
            "0, 10, 0",
            "4, 6, 4",
            "8, 12, 8",
            "2, 5, -1"
    })
    void getNumberOfVertices(int vertex, int edge, int expectedVertex) {
        Box box = new Box(vertex, edge);
        int numberOfVertices = box.getNumberOfVertices();
        Assertions.assertThat(numberOfVertices).isEqualTo(expectedVertex);
    }

    @DisplayName("isExist method")
    @ParameterizedTest
    @CsvSource({
            "0, 10, true",
            "4, 6, true",
            "8, 12, true",
            "2, 5, false"
    })
    void isExist(int vertex, int edge, boolean expectedExist) {
        Box box = new Box(vertex, edge);
        boolean exist = box.isExist();
        Assertions.assertThat(exist).isEqualTo(expectedExist);
    }

    @DisplayName("getArea method")
    @ParameterizedTest
    @CsvSource({
            "0, 10, 1256.637",
            "4, 6, 62.3538",
            "8, 12, 864.0",
            "2, 5, 0.0"
    })
    void getArea(int vertex, int edge, double expectedArea) {
        Box box = new Box(vertex, edge);
        double exist = box.getArea();
        Assertions.assertThat(exist).isCloseTo(expectedArea, Percentage.withPercentage(0.0001d));
    }
}