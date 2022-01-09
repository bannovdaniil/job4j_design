package ru.job4j.io;

import java.io.FileInputStream;

public class EvenNumberFile {
    private static void printEven(String str) {
        for (var s : str.split(System.lineSeparator())) {
            if (s != null) {
                try {
                    Integer num = Integer.parseInt(s);
                    if (num % 2 == 0) {
                        System.out.println(num);
                    }
                } catch (NumberFormatException err) {
                    err.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        try (FileInputStream in = new FileInputStream("even.txt")) {
            StringBuilder text = new StringBuilder();
            int read;
            while ((read = in.read()) != -1) {
                text.append((char) read);
            }
            printEven(text.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
