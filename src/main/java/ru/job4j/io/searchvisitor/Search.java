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
        String extFile = checkExt(args[1]);
        Path start = checkFile(args[0]);
        search(start, p -> p.toFile().getName()
                .endsWith("." + extFile))
                .forEach(System.out::println);
    }

    /**
     * Проверка расширения, на валидность
     * расширение не может содержать спецсимволы,
     * на работоспособность не повлияет, но у нас нет реализации масок.
     *
      * @param extFile - расширение
     * @return - расширение
     */
    private static String checkExt(String extFile) {
        if (extFile.matches(".*[<>:\"\\|\\?\\*\\.].*")) {
            throw new IllegalArgumentException(
                    String.format("EXT [%s] contains one of the chars: .<>:\"|?*", extFile));
        }
        return extFile;
    }

    /**
     * Проводит проверку директории, если ошибки тогда бросаем исключение.
     * 1. такая область на диске есть
     * 2. эта область папка.
     * Если все хорошо возвращаем дескриптор.
     *
     * @param folderName - аргументы командной строки.
     * @return - File
     */
    private static Path checkFile(String folderName) {
        File pathDir = new File(folderName);
        if (!pathDir.exists()) {
            throw new IllegalArgumentException(String.format("Not exist %s",
                    pathDir.getAbsoluteFile()));
        }
        if (!pathDir.isDirectory()) {
            throw new IllegalArgumentException(String.format("Not directory %s",
                    pathDir.getAbsoluteFile()));
        }
        return Paths.get(folderName);
    }

    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }
}