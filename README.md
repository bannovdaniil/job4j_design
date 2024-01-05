# job4j_design

## О проекте

Работа с базой postgreSQL, сокеты, jdbc, парсер базы.
JSON, java.io, java.nio, collections + map

1. Что такое итератор. [ru.job4j.it.BackwardArrayIt]
    - шаблон - "Итератор".
2. Итератор для двухмерного массива int[][]. [ru.job4j.it.MatrixIt]
    - Шаблон итератор можно применить для любой структуры.
3. Итератор четных чисел   [ru.job4j.iterator.EvenNumbersIterator]
    - Создать итератор, возвращающий только четные цифры.
4. FlatMap для Iterator<Iterator>   [ru.job4j.iterator.FlatMap]
    - В Stream API есть метод flatMap. Он позволяет получить из элемента потока другой поток.
5. Итератор не-null значений [ru.job4j.iterator.NonNullIterator]
    - Создать итератор, возвращающий не-null значения.
6. Циклический итератор [ru.job4j.iterator.CyclicIterator]
    - В этом задании вам надо реализовать так называемый замкнутый , или циклический, итератор.
7. Балансир [ru.job4j.iterator.Balancer]
    - В этом задании надо реализовать балансир. Это класс, который равномерно распределяет данные из итератора по
      переданным ему спискам.

### 2.1.1. AssertJ

1. Настройка проекта.
2. Подключение зависимостей в pom.xml
3. Утверждения с примитивными типами [ru.job4j.assertj.Box]
   Поговорим о структуре тестового метода. Тесты желательно писать в одном стиле. Хорошо зарекомендовал себя шаблон
   тестов, получивший название AAA - Arrange, Act, Assert.
   На этапе Arrange производится подготовка данных для проверяемого действия - создаются нужные объекты, поля
   объектов наполняются тестовыми данными.
   Этап Act - это выполнение действия объекта, которое должно будет оцениваться, и сохранение результата этого
   действия.
   Этап Assert - это проверка соответствие фактического результата действия ожидаемому результату.
   