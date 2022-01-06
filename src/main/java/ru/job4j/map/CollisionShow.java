package ru.job4j.map;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

public class CollisionShow {
    public static void main(String[] args) {
        User user1 = new User("Семён", 3,
                new GregorianCalendar(2022, Calendar.JANUARY, 6));
        User user2 = new User("Семён", 3,
                new GregorianCalendar(2022, Calendar.JANUARY, 6));
        Map<User, Object> map = new HashMap<>();
        map.put(user1, new Object());
        map.put(user2, new Object());
        System.out.println(map);
        for (var u : map.keySet()) {
            System.out.println(u.hashCode() + ":" + u + ": " + map.get(u));
        }
    }
}
