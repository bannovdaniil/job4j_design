package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Analizy {
    private StringBuilder readFile(String fName) {
        StringBuilder log = new StringBuilder();
        try (BufferedReader inFile = new BufferedReader(new FileReader(fName))) {
            inFile.lines().forEach(s -> {
                log.append(s);
                log.append(System.lineSeparator());
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return log;
    }

    private void writeFile(String fName, List<String> log) {
        try (PrintWriter outFile = new PrintWriter(new FileOutputStream(fName))) {
            log.forEach(outFile::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 1. читаем
     * 2. коды ошибок вынес в List более универсально.
     * 3. собираем лист.
     *  3.1. код найден?
     *  3.2. если нет времени, фиксируем время.
     *  3.3. если сервер поднялся, и время старта есть
     *  3.4 фиксируем время поднятия сервера.
     * если сервер еще лежит, значит оставляем только одно время, хз?
     * по логике верно, по ТЗ такого нет.
     * 4. пишем в файл.
     *
     * @param source файл для чтения
     * @param target файл для записи
     */
    public void unavailable(String source, String target) {
        List<String> errorCode = List.of("400", "500");
        StringBuilder log = readFile(source);
        System.out.println(log);
        List<String> logList = new ArrayList<>();
        StringBuilder startTime = new StringBuilder();
        for (String s : log.toString().split(System.lineSeparator())) {
            if (s != null && !s.isEmpty()) {
                String[] spLog = s.split(" ", 2);
                if (errorCode.contains(spLog[0])) {
                    if (startTime.isEmpty()) {
                        startTime.append(spLog[1]);
                        startTime.append(";");
                    }
                } else if (!startTime.isEmpty()) {
                    startTime.append(spLog[1]);
                    startTime.append(";");
                    logList.add(startTime.toString());
                    startTime.setLength(0);
                }
            }
        }
        if (!startTime.isEmpty()) {
            startTime.append(";");
            logList.add(startTime.toString());
        }
        writeFile(target, logList);
    }

    public static void main(String[] args) {
        try (PrintWriter out = new PrintWriter(new FileOutputStream("log-20210109.log"))) {
            out.println("400 10:56:01");
            out.println("200 10:57:01");
            out.println("500 10:58:01");
            out.println("200 10:59:01");
            out.println("400 11:01:02");
            out.println("400 11:02:02");
        } catch (Exception e) {
            e.printStackTrace();
        }
        Analizy anal = new Analizy();
        anal.unavailable("log-20210109.log", "unavailable.csv");
    }
}
