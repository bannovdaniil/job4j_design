package ru.job4j.io.filefind;

import java.io.File;

public class HelpString {
    private final String helpAgrs;

    public HelpString() {
        String ln = System.lineSeparator();
        this.helpAgrs = String.format("%1$sjava -jar find.jar -d=[path]"
                        + " -n=[file] -t=[mask|name|regex] -o=[filename]log.txt"
                        + "%1$s-d - директория, в которой начинать поиск."
                        + "-d=c:" + File.separatorChar
                        + "%1$s-n - имя файла, маска, либо регулярное выражение."
                        + " -n=*.txt"
                        + "%1$s-t - тип поиска: mask искать по маске, name по полному"
                        + " совпадение имени, regex по регулярному выражению."
                        + " -t=mask"
                        + "%1$s-o - результат записать в файл."
                        + " -s=out.txt | -s=stdout",
                ln);
    }

    public String getHelpAgrs() {
        return helpAgrs;
    }
}
