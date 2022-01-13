package ru.job4j.io.serialization.json;

import org.json.JSONPropertyIgnore;

public class RecursionA {
    private RecursionB b;

    @JSONPropertyIgnore
    public RecursionB getB() {
        return b;
    }

    public void setB(RecursionB b) {
        this.b = b;
    }

    public String getHello() {
        return "Hello";
    }

}
