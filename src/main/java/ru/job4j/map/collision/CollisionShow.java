package ru.job4j.map.collision;

import java.util.*;

public class CollisionShow {
    public static List<Character> symbols(String string) {
        //.collect(Collectors.toList());
        return null;
    }

    public static void main(String[] args) {
        User user1 = new User("Семён", 3,
                new GregorianCalendar(2022, Calendar.JANUARY, 6));
        User user2 = new User("Семён", 3,
                new GregorianCalendar(2022, Calendar.JANUARY, 6));
        Map<User, Object> map = new HashMap<>();
        map.put(user1, "1");
        map.put(user2, "2");
        System.out.println(map);
        for (var u : map.keySet()) {
            System.out.println(u.hashCode() + ":" + u + ": " + map.get(u));
        }
        System.out.println((long) Integer.MAX_VALUE + (long) Integer.MAX_VALUE);
    }
}
