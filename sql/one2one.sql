create table numberavto(
    id serial primary key,
    number varchar(30)
);

create table cars(
    id serial primary key,
    name varchar(255),
    color varchar(255),
    numberavto_id int references numberavto(id) unique
);

create table numbertocars(
    id serial primary key,
		numberavto_id int references numberavto(id) unique,
    car_id int references cars(id) unique
);

insert into numberavto(number) values ('р021рр197');
insert into cars(name, color, numberavto_id) VALUES ('BMW 3', 'red', 1);
insert into numbertocars(numberavto_id, car_id) VALUES (1, 1);

select * from cars;
select * from numberavto;
select * from numbertocars;