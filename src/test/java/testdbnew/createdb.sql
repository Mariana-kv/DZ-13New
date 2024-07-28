create table teachers (
id SERIAL primary key,
firstName VARCHAR (255),
age INTEGER);



insert into teachers (firstName, age)
values
('Mark', 21),
('Andrew', 36),
('Bella', 74);