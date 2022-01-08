package ru.job4j.question;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Analize {
    /**
     * 1. если нет ключа в map => added =1
     * 2. если ключ есть то Имена совпадают? => changed = 1
     * 3. если ключи совпали и имена совпали.
     * 4. на всех 3х пунктах убираем обработанные элементы.
     * 5. если после прохода, что-то осталось значит есть удаленные элементы.
     * ---
     * Что оптимизировал map наполняю с помощью steam()
     * ---
     * Возможно все это можно было бы реализовать спомощью stream(), но
     * тогда не факт, что осталась бы линейная зависимость.
     * ---
     * еще вариант, удалить из одного сета все вхождения второго.
     * 1. removeAll если размер сета == 0 изменений нет
     * 2. если что-то осталось. Это или удалили или изменили.
     * И вот тут все равно делать loop по set и смотреть вхождения.
     * --
     * 0. Оптимизация родилась в процессе. из set вычитаем другой set и тогда можно
     * убрать 1 итерацию #3 (previous.removeAll(current);) коллекция immunable
     * идея отпадает.
     * Оптимизация .filter(n -> !current.contains(n)) не сработала, перестал считать
     * добавленные, что само по себе логично.
     * Идея такая, раз в импортах указана map, загоняем туда то что было (is, name)
     * идем, по списку то что стало
     *--
     * а если такой вариант? добавлять не по id в мапу а самих Users. Тогда какой смысл в мапе?
     * берем с такимже успехом List или Set и идем по нему, итераций будет столько же.
     * @param previous - было
     * @param current  - стало
     * @return - состояния(добавлен(0|1), изменен(0|1), удален(0|1))
     */
    public static Info diff(Set<User> previous, Set<User> current) {
        int added = 0;
        int changed = 0;
        Map<Integer, String> map = previous.stream()
                .collect(Collectors.toMap(User::getId, User::getName));
        for (var curUser : current) {
            int uId = curUser.getId();
            if (!map.containsKey(uId)) {
                added = 1;
                map.remove(uId);
            } else if (!map.get(uId).equals(curUser.getName())) {
                changed = 1;
                map.remove(uId);
            } else {
                map.remove(uId);
            }
        }
        int deleted = map.size() > 0 ? 1 : 0;
        return new Info(added, changed, deleted);
    }
}
