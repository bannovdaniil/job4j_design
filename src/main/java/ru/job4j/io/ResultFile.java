package ru.job4j.io;

import java.io.FileOutputStream;

public class ResultFile {
    private static boolean multipleToFile(int size) {
        boolean res = true;
        StringBuilder rowStr = new StringBuilder();
        for (int row = 0; row < size; row++) {
            for (int cell = 0; cell < size; cell++) {
                rowStr.append((row + 1) * (cell + 1));
                rowStr.append("\t");
            }
            rowStr.append(System.lineSeparator());
        }
        try (FileOutputStream out = new FileOutputStream("result.txt")) {
            out.write(rowStr.toString().getBytes());
        } catch (Exception e) {
            e.printStackTrace();
            res = false;
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(multipleToFile(25) ? "Ok" : "Error");
    }
}
