package ru.job4j.io.serialization.json;

import java.util.Arrays;

public class User {
    private String name;
    private int age;
    private String[] children;
    private boolean java;
    private Address address;

    public User(String name, int age, String[] children, boolean java, Address address) {
        this.age = age;
        this.name = name;
        this.children = children;
        this.java = java;
        this.address = address;
    }

    @Override
    public String toString() {
        return "User{"
                + "name='" + name + '\''
                + ", age=" + age
                + ", children=" + Arrays.toString(children)
                + ", java=" + java
                + ", adress=" + address
                + '}';
    }
}
