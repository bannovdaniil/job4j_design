SELECT AVG(price) FROM devices;

SELECT person.name, AVG(dev.price)
FROM people AS person
INNER JOIN devices_people AS dp
ON person.id = dp.people_id
INNER JOIN  devices AS dev
ON dev.id = dp.device_id
GROUP BY person.name
ORDER BY person.name ASC;

SELECT person.name AS person, AVG(dev.price) as avg_price
FROM people AS person
INNER JOIN devices_people AS dp
ON person.id = dp.people_id
INNER JOIN  devices AS dev
ON dev.id = dp.device_id
GROUP BY person.name
HAVING AVG(dev.price)>5000
ORDER BY avg_price ASC;

