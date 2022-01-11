package ru.job4j.io;

import java.util.HashMap;
import java.util.Map;

public class ArgsName {
    private String message = "Usage: prog [-name1=param [-name2=param [...]]]";
    private final Map<String, String> values = new HashMap<>();

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
            if (!s.startsWith("-") || !s.contains("=")) {
                throw new IllegalArgumentException(message);
            }
            String[] par = s.substring(1).split("=", 2);
            if (par[0].length() == 0 || par[1].length() == 0) {
                throw new IllegalArgumentException(message);
            }
            values.putIfAbsent(par[0], par[1]);
        }
    }

    public static ArgsName of(String[] args) {
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    public static ArgsName of(String[] args, String message) {
        ArgsName names = new ArgsName();
        names.message = message;
        names.parse(args);
        return names;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[]{"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));

        ArgsName zip = ArgsName.of(new String[]{"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }
}
