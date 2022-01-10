package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

public class DuplicatesFinder {
    public static void main(String[] args) throws IOException {
        DuplicatesVisitor ddFinder = new DuplicatesVisitor();
        Files.walkFileTree(Path.of(".\\.."), ddFinder);
        HashMap<FileProperty, List<Path>> ddFiles = ddFinder.getDoubles();

        for (var fp : ddFiles.keySet()) {
            System.out.println(fp);
            ddFiles.get(fp).forEach(System.out::println);
        }
    }
}
