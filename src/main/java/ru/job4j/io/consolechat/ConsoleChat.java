package ru.job4j.io.consolechat;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class ConsoleChat {
    private final String path;
    private final String botAnswers;
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    /**
     * - run(), содержит логику чата;
     * - пользователь вводит слово-фразу, программа берет случайную фразу из
     * текстового файла и выводит в ответ.
     * - программа замолкает если пользователь вводит слово «стоп», при этом он
     * может продолжать отправлять сообщения в чат.
     * - если пользователь вводит слово «продолжить», программа снова начинает отвечать.
     * - при вводе слова «закончить» программа прекращает работу.
     * - запись диалога, включая слова-команды стоп/продолжить/закончить должны быть
     * записаны в текстовый лог.
     */
    public void run() {
        List<String> botPhrases = readPhrases();
        List<String> chatHistory = new ArrayList<>();
        Random r = new Random();
        boolean stop = false;
        boolean exit = false;
        Scanner scanner = new Scanner(System.in);
        showHelp();
        do {
            System.out.print(">");
            String command = scanner.nextLine();
            switch (command) {
                case OUT:
                    exit = true;
                    break;
                case STOP:
                    stop = true;
                    System.out.println("=== Бот остановлен, включить бота"
                            + " снова введите \"продолжить\"");
                    break;
                case CONTINUE:
                    stop = false;
                    System.out.println("=== Бот включен");
                    break;
                default:
            }
            chatHistory.add(command);
            if (!stop && !exit) {
                Optional<String> answerNotNull = botPhrases.stream()
                        .skip(r.nextInt(botPhrases.size()))
                        .findFirst();
                String answer;
                answer = answerNotNull.orElseGet(
                        () -> "Error: No elements in Phrasese file (" + botAnswers + ").");
                System.out.println(answer);
                chatHistory.add(answer);
            }
        } while (!exit);
        saveLog(chatHistory);
    }

    private void showHelp() {
        System.out.println("Команды чата:");
        System.out.println("закончить - выйти из чата");
        System.out.println("стоп - отключить бота");
        System.out.println("продолжить - включить бота");
    }

    /**
     * - readPhrases(), читает фразы из файла;
     *
     * @return - фразы чата
     */
    private List<String> readPhrases() {
        List<String> phrases = new ArrayList<>();
        Path file = Paths.get(botAnswers);
        if (!Files.exists(file)) {
            createPhrases();
        }
        try (BufferedReader in = new BufferedReader(
                new FileReader(file.toFile(), StandardCharsets.UTF_8))) {
            String s = in.readLine();
            while (s != null) {
                phrases.add(s);
                s = in.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return phrases;
    }

    /**
     * кто-то же его должен создать :)
     */
    private void createPhrases() {
        try (PrintWriter out = new PrintWriter(
                new FileWriter(botAnswers, StandardCharsets.UTF_8), true)) {
            out.println("Не подскажите который сейчас час?");
            out.println("Как вам погода?");
            out.println("Меня звать Алеша, а вас как?");
            out.println("Ты хочешь я расскажу тебе сказку?");
            out.println("От топота копыт, пыль по полю летит.");
            out.println("Случайности не случайны...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * - saveLog(), сохраняет лог чата в файл.
     *
     * @param log - фразы чата
     */
    private void saveLog(List<String> log) {
        try (PrintWriter out = new PrintWriter(
                new FileWriter(path, StandardCharsets.UTF_8), true)) {
            log.forEach(out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("./chathistory.txt", "./fraze.txt");
        cc.run();
    }
}
