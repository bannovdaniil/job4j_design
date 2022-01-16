 create table pencel(
     id serial primary key,
     name varchar(255)
 );
 
 create table color(
     id serial primary key,
     name varchar(255)
 );
 
 create table pencel_color(
     id serial primary key,
     pencel_id int references pencel(id),
     color_id int references color(id)
 );

insert into pencel(name) values ('Schneider');
insert into color(name) VALUES ('red');
insert into pencel_color(pencel_id, color_id) VALUES (1, 1);

select * from pencel;
select * from color;
select * from pencel_color;