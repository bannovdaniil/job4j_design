package ru.job4j.io.serialization.json;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "address")
public class Address {
    @XmlAttribute
    String street;
    @XmlAttribute
    int home;

    public Address() {
    }

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
