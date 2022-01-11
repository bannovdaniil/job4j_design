package ru.job4j;

public class PiNumber {
    public static double pi(long times) {
        double result = 0;
        double del = 4.0;
        double zna = 1.0;
        for (long i = 0; i < times; i++) {
            result += ((i % 2 == 0) ? 1 : -1) * del / zna;
            zna += 2;
            if (i % 1000_000_000 == 0) {
                System.out.println(result);
            }
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println(pi(1_000_000_000_000L));
    }
}
