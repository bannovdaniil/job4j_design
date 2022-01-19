package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

public class Config {

    private final String path;
    private final Map<String, String> values = new HashMap<>();

    public Config(final String path) {
        this.path = path;
        this.load();
    }

    public void load() {
        try (BufferedReader inFile = new BufferedReader(new FileReader(path))) {
            String inStr = inFile.readLine();
            while (inStr != null) {
                inStr = inStr.strip();
                if (!inStr.startsWith("#") && !inStr.isEmpty()) {
                    if (inStr.indexOf('=') > 0) {
                        String[] splitStr = inStr.split("=", 2);
                        values.put(splitStr[0].strip(), splitStr[1].strip());
                    } else {
                        throw new IllegalArgumentException();
                    }
                }
                inStr = inFile.readLine();
            }
        } catch (FileNotFoundException err) {
            err.printStackTrace();
        } catch (IOException err) {
            err.printStackTrace();
        }
    }

    public String value(String key) {
        return values.containsKey(key) ? values.get(key) : null;
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out.toString();
    }

    public static void main(String[] args) {
        Config cfg = new Config("app.properties");
        cfg.load();
        System.out.println(cfg);
    }

}