package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;
import java.util.stream.Collectors;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    HashMap<FileProperty, List<Path>> ddFiles = new HashMap<>();
    List<Path> allFiles = new ArrayList<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty fp = new FileProperty(Files.size(file), file.getFileName().toString());
        List<Path> pathList = new ArrayList<>();
        if (ddFiles.containsKey(fp)) {
            pathList = ddFiles.get(fp);
        }
        pathList.add(file.toAbsolutePath());
        ddFiles.put(fp, pathList);
        return super.visitFile(file, attrs);
    }

    /**
     * Цель такая...
     * Надо вернуть, Имя файла если есть дубли, и list из путей ко всем этим файлам
     * Map< имя, и лист с путями>
     * 1. Во время поиска
     * Идем по списку и добавляем все в Мап
     * если ключ существует значит name&size файла совпали,
     * добавляем путь в список.
     * 2. После делаем проход №2 удаляем все ключи в которых список менее 2х единиц.
     * Хотел сначала это все сделать в поиске, но пока поиск не окончен, невозможно
     * пройти неполный список, решил, что правильнее сделать отдельный метод.
     * Т.к. функционал иной.
     *
     * @return - TreeMap<FileProperty, List<String>>
     */
    public HashMap<FileProperty, List<Path>> getDoubles() {
        return ddFiles.keySet()
                .stream()
                .filter(fp -> ddFiles.get(fp).size() > 1)
                .collect(Collectors.toMap(k -> k, v -> ddFiles.get(v), (k1, k2) -> k1,
                        HashMap::new));
    }

    public List<Path> getAllFiles() {
        return allFiles;
    }
}