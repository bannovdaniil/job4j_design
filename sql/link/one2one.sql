create table numberavto(
    id serial primary key,
    number varchar(30)
);

create table cars(
    id serial primary key,
    name varchar(255),
    color varchar(30),
    numberavto_id int references numberavto(id) unique
);

insert into numberavto(number) values ('р021рр197');
insert into cars(name, color, vin, numberavto_id) VALUES ('BMW 3', 'red', 1);

select * from cars;
select * from numberavto;
