select * from fauna where name LIKE '%fish%';
select * from fauna where avg_age>=10000 AND avg_age<=20000;
select * from fauna where discovery_date IS NULL;
select * from fauna where discovery_date < '1950-01-01';
