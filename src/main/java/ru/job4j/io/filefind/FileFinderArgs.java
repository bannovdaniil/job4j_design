package ru.job4j.io.filefind;

import java.util.HashMap;
import java.util.Map;

public class FileFinderArgs {
    private String message = "Usage: prog [-name1=param [-name2=param [...]]]";
    private final Map<String, String> values = new HashMap<>();

    /**
     * если ключ не обнаружен, ошибка
      * @param key - ключ
     * @return - значение ключа
     */
    public String get(String key) {
        if (!values.containsKey(key)) {
            throw new IllegalArgumentException(System.lineSeparator()
                    + "Parameter -" + key + " is absent " + message);
        }
        return values.get(key);
    }

    /**
     * 0. без параметров, бросить исключение
     * 1. определить первый символ -
     * 2. определить наличие =
     * 3. наличие параметра
     * 4. наличие значения
     * 5. в случаи неудачи бросаем IllegalArgumentException.
     *
     * @param args - агрументы комманденой строки
     */
    private void parse(String[] args) {
        if (args.length < 1) {
            throw new IllegalArgumentException(message);
        }
        for (String s : args) {
            checkParameter(s);
        }
    }

    /**
     * Проверка параметра на валидность и разборка на ключ/значение
      * @param s строка с параметром
     */
    private void checkParameter(String s) {
        if (!s.startsWith("-") || !s.contains("=")) {
            throw new IllegalArgumentException(message);
        }
        String[] par = s.substring(1).split("=", 2);
        if (par[0].length() == 0 || par[1].length() == 0) {
            throw new IllegalArgumentException(message);
        }
        values.putIfAbsent(par[0], par[1]);
    }

    public static FileFinderArgs of(String[] args) {
        FileFinderArgs names = new FileFinderArgs();
        names.parse(args);
        return names;
    }

    public static FileFinderArgs of(String[] args, String message) {
        FileFinderArgs names = new FileFinderArgs();
        names.message = message;
        names.parse(args);
        return names;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
