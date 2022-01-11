package ru.job4j.csv;

import ru.job4j.io.ArgsName;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class CSVReader {
    /**
     * какие мысли
     * 1. первая строка это имена колонок
     * 2. у нас есть фильтр, что именно собирать
     * 3. определяемся с индексами фильтров.
     * 4. разбиваем по delimiter
     *
     * @param argsName параметры
     * @throws Exception - прерывание любое
     */
    public void handle(ArgsName argsName) throws Exception {
        Path path = Paths.get(argsName.get("path"));
        String delimiter = argsName.get("delimiter");
        String out = argsName.get("out");
        List<String> filter = Arrays
                .stream(argsName.get("filter")
                        .split(",")).collect(Collectors.toList());
        List<Integer> filtNum = new ArrayList<>();
        StringBuilder result = new StringBuilder();
        try (var scanner = new Scanner(path)) {
            int count = 0;
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] fields = line.split(delimiter);
                if (count == 0) {
                    for (int i = 0; i < fields.length; i++) {
                        if (filter.contains(fields[i])) {
                            filtNum.add(i);
                        }
                    }
                }
                for (int i = 0; i < fields.length; i++) {
                    if (filtNum.contains(i)) {
                        result.append(fields[i]);
                        if (filtNum.indexOf(i) != filtNum.size() - 1) {
                            result.append(delimiter);
                        }
                    }
                }
                result.append(System.lineSeparator());
                count++;
            }
        }
        if ("stdout".equals(out)) {
            System.out.print(result);
        } else {
            saveFile(out, result.toString());
        }
    }

    private void saveFile(String fileName, String text) {
        try (BufferedWriter out = new BufferedWriter(new FileWriter(Paths.get(fileName).toFile()))) {
            out.write(text);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        ArgsName arg = ArgsName.of(args, System.lineSeparator()
                + "java -jar csvReader.jar -path=in.csv -delimiter=; -out=stdout -filter=f1,f2"
                + System.lineSeparator()
                + "-path=in.csv - input file"
                + System.lineSeparator()
                + "-delimiter=; - delimiter char"
                + System.lineSeparator()
                + "-out=out.csv - name stdout = to screen, else other file to save data"
                + System.lineSeparator()
                + "-filter=f1,f2 - save only fields f1 and f2, delimiter comma.");
        File file = new File(arg.get("path"));
        if (!file.exists()) {
            throw new IllegalArgumentException(String.format("Not exist %s",
                    file.getAbsoluteFile()));
        }
        if (file.isDirectory()) {
            throw new IllegalArgumentException(String.format("Is't file %s",
                    file.getAbsoluteFile()));
        }
        CSVReader csv = new CSVReader();
        csv.handle(arg);
    }
}
