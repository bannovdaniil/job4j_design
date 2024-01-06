package ru.job4j.assertj;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 4. Утверждения с исключениями
 * <p>
 * Напишите тесты, в которых проверьте генерацию всех исключений, которые должен генерировать класс NameLoad.
 * Проверьте, что в сообщении об ошибке передаются параметры, которые эту ошибку вызвали. Например, если передать
 * в метод parse строку с нарушением формата, например, такую  "key:value", то будет сгенерировано исключение и
 * в сообщении об ошибке будет строка "key:value", которая это исключение вызвала.
 * Этот факт надо проверить в тесте. Так же и для других исключений.
 */
public class NameLoad {
    private final Map<String, String> values = new HashMap<>();

    public void parse(String... names) {
        if (names.length == 0) {
            throw new IllegalArgumentException("Names array is empty");
        }
        values.putAll(Arrays.stream(names)
                .map(String::trim)
                .filter(this::validate)
                .map(line -> line.split("=", 2))
                .collect(Collectors.toMap(
                        e -> e[0],
                        e -> e[1],
                        (first, second) -> "%s+%s".formatted(first, second)
                )));
    }

    private boolean validate(String name) {
        if (!name.contains("=")) {
            throw new IllegalArgumentException(
                    "this name: %s does not contain the symbol '='".formatted(name));
        }
        if (name.startsWith("=")) {
            throw new IllegalArgumentException(
                    "this name: %s does not contain a key".formatted(name));
        }
        if (name.indexOf("=") == name.length() - 1) {
            throw new IllegalArgumentException(
                    "this name: %s does not contain a value".formatted(name));
        }
        return true;
    }

    public Map<String, String> getMap() {
        if (values.isEmpty()) {
            throw new IllegalStateException("collection contains no data");
        }
        return values;
    }
}