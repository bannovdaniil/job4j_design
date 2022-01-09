package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class LogFilter {
    public List<String> filter(String file) {
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

    public void save(List<String> log, String file) {
        try (PrintWriter pw =
                     new PrintWriter(new BufferedOutputStream(
                             new FileOutputStream(file)))) {
            log.forEach(pw::println);
        } catch (IOException err) {
            err.printStackTrace();
        }
    }

    public static void main(String[] args) {
        LogFilter logFilter = new LogFilter();
        List<String> logStr = logFilter.filter("log.txt");
        logStr.forEach(System.out::println);
        logFilter.save(logStr, "404.txt");
    }
}
