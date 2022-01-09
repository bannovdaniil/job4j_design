package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class LogFilter {
    public static List<String> filter(String file) {
        List<String> logLines = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(file))) {
            for (String s = in.readLine(); s != null; s = in.readLine()) {
                String[] splitLine = s.split(" ");
                if ("404".equals(splitLine[splitLine.length - 2])) {
                    logLines.add(s);
                }
            }
        } catch (Exception err) {
            err.printStackTrace();
        }
        return logLines;
    }

    public static void main(String[] args) {
        List<String> log = filter("log.txt");
        System.out.println(log);
    }
}
