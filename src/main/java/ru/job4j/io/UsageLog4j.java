package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.list.linkedlist.List;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * log4j:
 * private static final Logger LOG = LogManager.getLogger(UsageLog4j.class.getName());
 * slf4j:
 * private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());
 */
public class UsageLog4j {
    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        byte b = 1;
        char c = 'a';
        short s = 1;
        int i = 1;
        long l = 1;
        float f = 0.1f;
        double d = 0.1d;
        boolean magic = true;
        LOG.debug("byte: {}, char: {}, short: {}, int: {},"
                        + " long: {}, float: {}, double: {}, boolean {}",
                b, c, s, i, l, f, d, magic);
        var user = new Object() {
            private final String name = "Inline object name.";
        };
        LOG.debug("Object : {}, user.name : {}", user, user.name);
        String name = "Petr Arsentev";
        int age = 33;
        LOG.debug("User info name : {}, age : {}", name, age);
        double pi = 3.14159;
        LOG.debug("Pi constant {}", pi);
        int square = 20;
        LOG.debug("Square {}", square);
        int count = 5;
        String fruit = "mango";
        StringBuilder animal = new StringBuilder("Monkey");
        LOG.debug("animal: {}, eat: {}, cont: {}", animal, fruit, count);
        boolean result = false;
        LOG.debug("Result of operation: {}", result);
        ArrayList<String> list = new ArrayList<>();
        LOG.debug("List: {}", list);
        list.add("new string");
        LOG.debug("List: {} size: {}", list, list.size());
    }
}