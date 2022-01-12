package ru.job4j.io;

import java.io.File;

public class Dir {
    public static void main(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Root folder is null. Usage java -jar dir.jar ROOT_FOLDER.");
        }
        File file = checkFile(args[0]);
        for (File subfile : file.listFiles()) {
            if (subfile.isFile()) {
                System.out.printf("Name: %s Size : %s%n",
                        subfile.getName(),
                        subfile.length());
            }
        }
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
    private static File checkFile(String folderName) {
        File file = new File(folderName);
        if (!file.exists()) {
            throw new IllegalArgumentException(String.format("Not exist %s",
                    file.getAbsoluteFile()));
        }
        if (!file.isDirectory()) {
            throw new IllegalArgumentException(String.format("Not directory %s",
                    file.getAbsoluteFile()));
        }
        System.out.printf("size : %s%n", file.getTotalSpace());
        return file;
    }
}