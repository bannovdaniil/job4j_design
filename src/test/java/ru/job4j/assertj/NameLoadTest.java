package ru.job4j.assertj;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class NameLoadTest {

    @DisplayName("getMap для пустой мапы.")
    @Test
    void checkEmpty() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(nameLoad::getMap)
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("no data");
    }

    @DisplayName("parse с отсутвующими параметрами.")
    @Test
    void parseEmptyArguments() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(nameLoad::parse)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Names array is empty");
    }

    @DisplayName("parse с неправильным параметром.")
    @ParameterizedTest
    @CsvSource(value = {
            "`key:value`; `does not contain the symbol '='`",
            "`key=`     ; `does not contain a value`",
            "`=value`   ; `does not contain a key`"
    }, delimiter = ';', quoteCharacter = '`')
    void parseWrongArgument(String value, String exceptedMessage) {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(() -> nameLoad.parse(value))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("this name: ")
                .hasMessageContaining(value)
                .hasMessageContaining(exceptedMessage);
    }

}