package ru.job4j.io.filefind;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;
import java.util.regex.PatternSyntaxException;

/**
 * 1. Создать программу для поиска файла. Все классы, относящиеся к этому заданию должны быть в отдельном пакете
 * Важно! Допускается использование ранее созданных вами классов.
 * 2. Программа должна искать данные в заданном каталоге и подкаталогах.
 * 3. Имя файла может задаваться, целиком, по маске, по регулярному выражению(не обязательно).
 * 4. Программа должна собираться в jar и запускаться через
 * java -jar find.jar -d=c:/ -n=*.txt -t=mask -o=log.txt
 * Ключи
 * -d - директория, в которой начинать поиск.
 * -n - имя файла, маска, либо регулярное выражение.
 * -t - тип поиска: mask искать по маске, name по полному совпадение имени, regex по регулярному выражению.
 * -o - результат записать в файл.
 * 5. Программа должна записывать результат в файл.
 * 6. В программе должна быть валидация ключей и подсказка.
 */
public class FileFinder {
    public static void main(String[] args) throws IOException {
        String helpString = new HelpString().getHelpAgrs();
        FileFinderArgs arg = FileFinderArgs.of(args, helpString);
        String pathFinder = arg.get("d");
        String nameFinder = arg.get("n");
        String typeFinder = arg.get("t");
        String outFinder = arg.get("o");
        String regexp = selectType(nameFinder, typeFinder);
        Path startFolder = checkFile(pathFinder);
        System.out.println("Scan: " + pathFinder);
        List<Path> fileList;
        try {
            fileList = search(startFolder, p -> p.toFile().getName().matches(regexp));
        } catch (PatternSyntaxException err) {
            throw new IllegalArgumentException("Bad file name mask" + helpString);
        }
        if ("stdout".equals(outFinder)) {
            fileList.forEach(System.out::println);
        } else {
            try (PrintWriter pw = new PrintWriter(new FileOutputStream(outFinder))) {
                fileList.forEach(pw::println);
            }
        }
    }

    private static String selectType(String nameFinder, String typeFinder) {
        return switch (typeFinder) {
            case "name", "regex" -> nameFinder;
            case "mask" -> maskToRegex(nameFinder);
            default -> throw new IllegalArgumentException();
        };
    }

    /**
     * создает из маски regEx
     * * = .*
     * ? = ?
     * экранирование символов . ^ [ ] ( ) $
     *
     * @param nameFinder маска
     * @return - regex
     */
    private static String maskToRegex(String nameFinder) {
        nameFinder = nameFinder.replaceAll("([\\.\\^\\$\\[\\]\\(\\)])", "\\\\$1");
        nameFinder = nameFinder.replaceAll("\\*", ".*");
        nameFinder = nameFinder.replaceAll("\\?", ".");
        return "^" + nameFinder + "$";
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
        Path pathDir = Paths.get(folderName);
        if (!Files.exists(pathDir)) {
            throw new IllegalArgumentException(String.format("Not exist %s",
                    pathDir.toAbsolutePath()));
        }
        if (!Files.isDirectory(pathDir)) {
            throw new IllegalArgumentException(String.format("Not directory %s",
                    pathDir.toAbsolutePath()));
        }
        return pathDir;
    }

    /**
     * Запускаем поиск
     *
     * @param root      - папка с которой начинаем искать
     * @param condition - маска файлов
     * @return - список файлов
     */
    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        FileFinderVisitor searcher = new FileFinderVisitor(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }
}