package ru.job4j.io.serialization.json;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonJava {
    public static void main(String[] args) {
        JSONObject jsonAddress = new JSONObject("{\"street\":\"Moskovskay\",\"home\":44}");
        List<String> list = new ArrayList<>();
        list.add("Katy");
        list.add("Kristina");
        JSONArray jsonChildren = new JSONArray(list);
        JSONObject jsonObject = new JSONObject();
        final User user = new User("Daniil Bannov", 44,
                new String[]{"Катя", "Кристина"}, true,
                new Address("Leninskaya", 12));
        jsonObject.put("address", jsonAddress);
        jsonObject.put("name", user.getName());
        jsonObject.put("age", user.getAge());
        jsonObject.put("children", jsonChildren);
        jsonObject.put("java", user.isJava());
        System.out.println(jsonAddress);
        System.out.println(jsonChildren);
        System.out.println(jsonObject);
        System.out.println(new JSONObject(user));
    }
}
