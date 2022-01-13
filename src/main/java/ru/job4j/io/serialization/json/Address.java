package ru.job4j.io.serialization.json;

public class Address {
    String street;
    int home;

    public Address(String street, int home) {
        this.street = street;
        this.home = home;
    }

    @Override
    public String toString() {
        return "Address{"
                + "street='" + street + '\''
                + ", home=" + home
                + '}';
    }
}
