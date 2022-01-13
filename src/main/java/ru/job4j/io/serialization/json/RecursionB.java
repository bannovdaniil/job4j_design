package ru.job4j.io.serialization.json;

import org.json.JSONObject;

public class RecursionB {
    private RecursionA a;

    public RecursionA getA() {
        return a;
    }

    public void setA(RecursionA a) {
        this.a = a;
    }

    public static void main(String[] args) {
        RecursionA a = new RecursionA();
        RecursionB b = new RecursionB();
        a.setB(b);
        b.setA(a);

        System.out.println(new JSONObject(b));
    }
}
