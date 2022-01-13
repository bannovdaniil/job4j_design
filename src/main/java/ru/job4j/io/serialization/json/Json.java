package ru.job4j.io.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Arrays;

public class Json {
    public static void main(String[] args) {
        Object user = new User("Daniil Bannov", 44,
                new String[]{"Катя", "Кристина"}, true,
                new Address("Leninskaya", 12));
        final Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(user));
        final String userJson = "{"
                + "\"name\":\"Ivanov Ivan\",\"age\":22,"
                + "\"children\":[\"Гоша\",\"Кирилл\",\"Ангелина\"],"
                + "\"java\":false,\""
                + "adress\":{\"street\":\"Mashinostoiteley\",\"home\":57}}";
        final User newUser = gson.fromJson(userJson, User.class);
        System.out.println(newUser);
    }
}
