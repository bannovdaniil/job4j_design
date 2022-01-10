package ru.job4j.io.searchvisitor;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;

public class Search {
    public static void main(String[] args) throws IOException {
        if (args.length != 2) {
            throw new IllegalArgumentException(System.lineSeparator()
                    + "Usage: java -jar Search.jar <FOLDER> <EXT>"
                    + System.lineSeparator()
                    + "Example: java -jar Search.jar c:\\ java");
        }
        File pathDir = new File(args[0]);
        if (!pathDir.exists()) {
            throw new IllegalArgumentException(String.format("Not exist %s",
                    pathDir.getAbsoluteFile()));
        }
        if (!pathDir.isDirectory()) {
            throw new IllegalArgumentException(String.format("Not directory %s",
                    pathDir.getAbsoluteFile()));
        }
        String extFile = args[1];
        if (extFile.matches(".*[<>:\"\\|\\?\\*\\.].*")) {
            throw new IllegalArgumentException(
                    String.format("EXT [%s] contains one of the chars: .<>:\"|?*", extFile));
        }
        Path start = Paths.get(args[0]);
        search(start, p -> p.toFile().getName()
                .endsWith("." + extFile))
                .forEach(System.out::println);
    }

    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }
}