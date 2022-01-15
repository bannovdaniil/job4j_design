CREATE TABLE cars (
	id serial primary key,
	name varchar(255),
	color varchar(30),
	number varchar(20)
);
insert into cars(name, color, number) values('Toyota', 'white', 'р021рр177rus');
select * from cars;
update cars set color='red';
select * from cars;