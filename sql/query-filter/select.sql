-- 1. Написать запрос получение всех продуктов с типом "СЫР"
SELECT p.id, p.name, t.name AS type, p.expired_date, p.price 
FROM product AS p
INNER JOIN type AS t
ON UPPER(t.name) LIKE UPPER('сыр') AND p.type_id = t.id ;
-- 2. Написать запрос получения всех продуктов, у кого в имени есть слово "мороженое"
SELECT * 
FROM product AS p
WHERE UPPER(name) LIKE UPPER('%мороженое%');
-- 3. Написать запрос, который выводит все продукты, срок годности которых уже истек
SELECT * 
FROM product
WHERE expired_date < CURRENT_DATE;
-- 4. Написать запрос, который выводит самый дорогой продукт.
SELECT *
FROM product AS p
WHERE p.price = (SELECT MAX(price) FROM product);
-- ИЛИ
SELECT p.id, p.name, p.type_id, p.expired_date, p.price
FROM product AS p
INNER JOIN (
SELECT max(price) as maxprice
FROM product) as maxselect
ON p.price = maxselect.maxprice;
-- 5. Написать запрос, который выводит для каждого типа количество продуктов к нему принадлежащих. 
-- В виде имя_типа, количество
SELECT t.name, count(p.name)
FROM product AS p
INNER JOIN type AS t
ON t.id = p.type_id
GROUP BY t.name
ORDER BY t.name ASC;
-- 6. Написать запрос получение всех продуктов с типом "СЫР" и "МОЛОКО"
SELECT p.id, p.name, t.name AS type, p.expired_date, p.price 
FROM product AS p
INNER JOIN type AS t
ON (UPPER(t.name) LIKE UPPER('сыр') OR  UPPER(t.name) LIKE UPPER('молоко')) AND p.type_id = t.id ;
-- 7. Написать запрос, который выводит тип продуктов, которых осталось меньше 10 штук. 
-- Под количеством подразумевается количество продуктов определенного типа.
-- Например, если есть тип "СЫР" и продукты "Сыр плавленный" и "Сыр моцарелла",
-- которые ему принадлежат, то количество продуктов типа "СЫР" будет 2. 

SELECT t.name, countselect.count
FROM type AS t
INNER JOIN(
 SELECT type_id, COUNT(name) AS count
 FROM product
 GROUP BY type_id
-- HAVING COUNT(name)<10
) AS countselect
ON countselect.type_id = t.id AND countselect.count<10;
-- 8. Вывести все продукты и их тип.
SELECT p.id, p.name, t.name AS type, p.expired_date, p.price
FROM product AS p
INNER JOIN type AS t
ON p.type_id = t.id
ORDER BY t.name ASC;
