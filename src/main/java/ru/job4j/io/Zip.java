package ru.job4j.io;

import ru.job4j.io.searchvisitor.SearchFiles;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {
    public void packFiles(List<File> sources, File target, String pathFolder) {
        long fileSize = 0;
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(
                new FileOutputStream(target)))) {
            zip.setLevel(9);
            for (var source : sources) {
                System.out.print(source);
                fileSize += source.length();
                zip.putNextEntry(new ZipEntry(source.getPath()
                        .replace(pathFolder, "")));
                try (BufferedInputStream out = new BufferedInputStream(
                        new FileInputStream(source))) {
                    zip.write(out.readAllBytes());
                }
                System.out.println(" - OK");
                zip.closeEntry();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.printf("Added: %d files. Size: [%.2f Mb/%.2f Mb] Compress ratio: %.2f%%",
                sources.size(),
                fileSize / 1048576f,
                target.length() / 1048576f, (target.length() * 100.0 / fileSize)
        );
        System.out.println(System.lineSeparator() + "Zip file: " + target.getAbsoluteFile());
    }

    public void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(
                new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(out.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void checkFolders(String folder) {
        File file = new File(folder);
        if (!file.exists()) {
            throw new IllegalArgumentException(String.format("Not exist %s",
                    file.getAbsoluteFile()));
        }
        if (!file.isDirectory()) {
            throw new IllegalArgumentException(String.format("Not directory %s",
                    file.getAbsoluteFile()));
        }
    }

    public static void main(String[] args) throws IOException {
        ArgsName arg = ArgsName.of(args, System.lineSeparator()
                + "java -jar pack.jar -d=folderPath -e=EXT -o=ZipFileName"
                + System.lineSeparator()
                + "-d=folderPath - path to folder"
                + System.lineSeparator()
                + "-e=EXT - exclude file extension"
                + System.lineSeparator()
                + "-o=ZipfileName - name of .zip file"
                + System.lineSeparator()
                + "Example: java -jar pack.jar -d=C:\\projects -e=class -o=in.zip");
        String pathFolder = arg.get("d");
        String excludeExt = arg.get("e");
        String zipName = arg.get("o");
        Zip zip = new Zip();
        zip.checkFolders(pathFolder);

        Path startFolder = Paths.get(pathFolder);
        SearchFiles searcher = new SearchFiles(p -> !p.toFile().getName()
                .endsWith("." + excludeExt));
        Files.walkFileTree(startFolder, searcher);
        if (!zipName.endsWith(".zip")) {
            zipName += ".zip";
        }
        System.out.println("Create archive file: " + zipName);
        System.out.println("Adding folders: " + pathFolder);
        zip.packFiles(
                searcher.getPaths().stream()
                        .map(Path::toFile)
                        .collect(Collectors.toList()),
                new File(zipName),
                pathFolder
        );
    }
}
