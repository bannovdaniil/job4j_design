create table fauna (
    id serial primary key,
    name text,
    avg_age int,
    discovery_date date
);

insert into fauna(name, avg_age, discovery_date) values("Жираф", 15,  date '2014-09-04');
insert into fauna(name, avg_age, discovery_date) values("Слон", 20,  date '2017-10-01');
insert into fauna(name, avg_age, discovery_date) values("Коза", 12,  date '2012-12-02');
insert into fauna(name, avg_age, discovery_date) values("Курица", 5,  date '2018-05-11');
insert into fauna(name, avg_age, discovery_date) values("golden fish chine", 10,  date '2019-04-21');
insert into fauna(name, avg_age, discovery_date) values("Тигр", 9,  date '2022-01-10');
