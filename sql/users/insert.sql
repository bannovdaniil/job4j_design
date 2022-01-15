insert into roles(name) values('Administrator');
insert into rules(name) values('ALL');
insert into role_rules(roles_id, rules_id) values(1, 1);
insert into users(name, roles_id) values('Ivanov Ivan', 1);
insert into state(name) values('Доставляется');
insert into category(name) values('Канцелярия');
insert into item(name, user_id, category_id, state_id) values('Ручка Parker', 1, 1, 1);
insert into comments(name, item_id) values('у метро в 8:00', 1);
insert into attachs(name, item_id) values('img.png', 1);

