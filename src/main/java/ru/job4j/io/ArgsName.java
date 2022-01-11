package ru.job4j.io;

import java.util.HashMap;
import java.util.Map;

public class ArgsName {

    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        return values.get(key);
    }

    /**
     * 0. безпараметров, просить мсключение
     * 1.опеределить первый символ -
     * 2.определить наличие =
     * 3.наличие параметра
     * 4.наличие значения
     * 5. в случаи неудачи бросам IllegalArgumentException.
     *
     * @param args - агрументы комманденой строки
     */

    private void parse(String[] args) {
        if (args.length < 1) {
            throw new IllegalArgumentException("Usage: prog [-name1=param [-name2=param [...]]]");
        }
        for (String s : args) {
            if (!s.startsWith("-") || !s.contains("=")) {
                throw new IllegalArgumentException("Usage: prog [-name1=param [-name2=param [...]]]");
            }
            String[] par = s.substring(1).split("=", 2);
            if (par[0].length() == 0 || par[1].length() == 0) {
                throw new IllegalArgumentException("Usage: prog [-name1=param [-name2=param [...]]]");
            }
            values.putIfAbsent(par[0], par[1]);
        }
    }

    public static ArgsName of(String[] args) {
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[]{"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));

        ArgsName zip = ArgsName.of(new String[]{"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }
}
