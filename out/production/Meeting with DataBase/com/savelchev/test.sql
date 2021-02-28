create table users (
user_id serial primary key,
user_name varchar (20) unique not null,
email varchar (50) unique not null
);

insert into users (user_name, email)
values
('maksim', 'maxim@gmail.com'),
('alena','alenka@mail.ru'),
('egor','egor@gmail.com'),
('alisa','maksimovna@mail.com');

select * from users;

select user_name from users where user_id = 2;
