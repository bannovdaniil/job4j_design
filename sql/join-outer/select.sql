-- 2. Выполнить запросы с left, rigth, full, cross соединениями
SELECT *
from departments AS dep
LEFT OUTER JOIN emploers AS emp
ON dep.dep_id = emp.dep_id
ORDER BY dep.name;

SELECT *
from departments AS dep
RIGHT OUTER JOIN emploers AS emp
ON dep.dep_id = emp.dep_id
ORDER BY dep.name;

SELECT *
from departments AS dep
FULL OUTER JOIN emploers AS emp
ON dep.dep_id = emp.dep_id
ORDER BY dep.name;

SELECT *
from departments AS dep
CROSS JOIN emploers AS emp
ORDER BY dep.name;

-- 3. Используя left join найти департаменты, у которых нет работников
SELECT *
from departments AS dep
LEFT OUTER JOIN emploers AS emp
ON dep.dep_id = emp.dep_id
WHERE emp.dep_id is null
ORDER BY dep.name;

-- 4. Используя left и right join написать запросы, которые давали бы одинаковый результат 
-- (порядок вывода колонок в эти запросах также должен быть идентичный). 
SELECT *
from departments AS dep
LEFT OUTER JOIN emploers AS emp
ON dep.dep_id = emp.dep_id
ORDER BY dep.name;

SELECT dep.dep_id, dep.name, emp.emp_id, emp.name, emp.dep_id
from emploers AS emp
RIGHT OUTER JOIN departments AS dep
ON dep.dep_id = emp.dep_id
ORDER BY dep.name;

-- 5. Создать таблицу teens с атрибутами name, gender и заполнить ее. 
-- Используя cross join составить все возможные разнополые пары
SELECT t1.name, t2.gender
FROM teens AS t1
CROSS JOIN teens AS t2;

