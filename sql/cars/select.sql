-- 1) Вывести список всех машин и все привязанные к ним детали.
SELECT car.name, cb.name, eng.name, gb.name
FROM cars AS car
INNER JOIN carbody AS cb
ON car.carbody_id = cb.id
INNER JOIN engine AS eng
ON car.engine_id = eng.id
INNER JOIN gearbox AS gb
ON car.gearbox_id = gb.id;

-- 2) Вывести отдельно детали (1 деталь - 1 запрос), которые не используются НИ в одной машине, кузова, 
-- двигатели, коробки передач.

SELECT cb.id, cb.name
FROM carbody AS cb
LEFT OUTER JOIN cars AS car
ON cb.id = car.carbody_id
WHERE car.carbody_id IS NULL;

SELECT eng.id, eng.name
FROM engine AS eng
LEFT OUTER JOIN cars AS car
ON eng.id = car.engine_id
WHERE car.engine_id IS NULL;

SELECT gb.id, gb.name
FROM gearbox AS gb
LEFT OUTER JOIN cars AS car
ON gb.id = car.gearbox_id
WHERE car.gearbox_id IS NULL;
