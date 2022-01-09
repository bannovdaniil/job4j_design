package ru.job4j.io;

import java.io.FileInputStream;

public class EvenNumberFile {
    private void printEven(String str) {
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

    private void readFile(String fName) {
        try (FileInputStream in = new FileInputStream(fName)) {
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

    public static void main(String[] args) {
        EvenNumberFile evenFile = new EvenNumberFile();
        evenFile.readFile("even.txt");
    }
}
