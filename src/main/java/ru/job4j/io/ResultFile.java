package ru.job4j.io;

import java.io.FileOutputStream;

public class ResultFile {
    private String generationMatrix(int size) {
        StringBuilder rowStr = new StringBuilder();
        for (int row = 0; row < size; row++) {
            for (int cell = 0; cell < size; cell++) {
                rowStr.append((row + 1) * (cell + 1));
                rowStr.append("\t");
            }
            rowStr.append(System.lineSeparator());
        }
        return rowStr.toString();
    }

    private boolean multipleToFile(int size) {
        boolean res = true;
        String rowStr = generationMatrix(size);
        try (FileOutputStream out = new FileOutputStream("result.txt")) {
            out.write(rowStr.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
            res = false;
        }
        return res;
    }

    public static void main(String[] args) {
        ResultFile rf = new ResultFile();
        System.out.println(rf.multipleToFile(10) ? "Ok" : "Error");
    }
}
