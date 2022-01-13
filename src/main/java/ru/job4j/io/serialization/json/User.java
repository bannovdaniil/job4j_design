package ru.job4j.io.serialization.json;

import javax.xml.bind.annotation.*;
import java.util.Arrays;

@XmlRootElement(name = "user")
@XmlAccessorType(XmlAccessType.FIELD)
public class User {
    @XmlAttribute
    private String name;
    @XmlAttribute
    private int age;
    @XmlAttribute
    private boolean java;
    @XmlElementWrapper(name = "childrens")
    @XmlElement(name = "children")
    private String[] children;
    private Address address;

    public User() {
    }

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

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public boolean isJava() {
        return java;
    }

    public String[] getChildren() {
        return children;
    }

    public Address getAddress() {
        return address;
    }
}
