create table typeofcar(
    id serial primary key,
    type_name varchar(255)
);

create table cars(
    id serial primary key,
    name varchar(255),
    type_id int references typeofcar(id)
);

insert into typeofcar(type_name) values ('sedan');
insert into cars(name, type_id) VALUES ('BMW 3', 1);
insert into cars(name, type_id) VALUES ('Lada vesta', 1);

select * from cars;
select * from typeofcar where id in (select id from cars);
