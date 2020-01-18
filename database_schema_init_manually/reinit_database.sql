create schema if not exists University collate koi8r_bin;

use University;

create table if not exists Students
(
	id int auto_increment
		primary key,
	name varchar(250) not null,
	age int not null
);



DELETE FROM University.Students;


INSERT INTO University.Students (id, name, age) VALUES (9, 'Name01', 11);
INSERT INTO University.Students (id, name, age) VALUES (10, 'Name02', 11);
INSERT INTO University.Students (id, name, age) VALUES (11, 'Name03', 11);
INSERT INTO University.Students (id, name, age) VALUES (12, 'Name04', 11);
INSERT INTO University.Students (id, name, age) VALUES (13, 'Name05', 11);
INSERT INTO University.Students (id, name, age) VALUES (14, 'Name06', 11);
INSERT INTO University.Students (id, name, age) VALUES (15, 'Name07', 12);
INSERT INTO University.Students (id, name, age) VALUES (16, 'Name08', 11);
INSERT INTO University.Students (id, name, age) VALUES (17, 'Name09', 11);
INSERT INTO University.Students (id, name, age) VALUES (18, 'Name10', 11);
INSERT INTO University.Students (id, name, age) VALUES (19, 'Name11', 11);
INSERT INTO University.Students (id, name, age) VALUES (20, 'Name12', 11);
INSERT INTO University.Students (id, name, age) VALUES (21, 'Name13', 11);
INSERT INTO University.Students (id, name, age) VALUES (22, 'Name14', 11);
INSERT INTO University.Students (id, name, age) VALUES (23, 'Name15', 11);
INSERT INTO University.Students (id, name, age) VALUES (24, 'Name16', 11);
INSERT INTO University.Students (id, name, age) VALUES (25, 'Name17', 11);
INSERT INTO University.Students (id, name, age) VALUES (26, 'Name18', 11);
INSERT INTO University.Students (id, name, age) VALUES (27, 'mugabe', 111);
INSERT INTO University.Students (id, name, age) VALUES (28, 'Name01!', 12);
INSERT INTO University.Students (id, name, age) VALUES (29, 'Name02!', 13);
INSERT INTO University.Students (id, name, age) VALUES (30, 'Name03!', 14);
INSERT INTO University.Students (id, name, age) VALUES (31, 'Name04!', 15);
INSERT INTO University.Students (id, name, age) VALUES (32, 'Name05!', 16);
INSERT INTO University.Students (id, name, age) VALUES (33, 'Name06!', 17);
INSERT INTO University.Students (id, name, age) VALUES (34, 'Name07!', 18);
INSERT INTO University.Students (id, name, age) VALUES (35, 'Name08!', 19);
INSERT INTO University.Students (id, name, age) VALUES (36, 'Name09!', 20);
INSERT INTO University.Students (id, name, age) VALUES (37, 'Name10!', 21);
INSERT INTO University.Students (id, name, age) VALUES (38, 'Name11!', 22);
INSERT INTO University.Students (id, name, age) VALUES (39, 'Name12!', 23);
INSERT INTO University.Students (id, name, age) VALUES (40, 'Name13!', 24);
INSERT INTO University.Students (id, name, age) VALUES (41, 'Name14!', 25);
INSERT INTO University.Students (id, name, age) VALUES (42, 'Name15!', 26);
INSERT INTO University.Students (id, name, age) VALUES (43, 'Name16!', 27);
INSERT INTO University.Students (id, name, age) VALUES (44, 'Name17!', 28);
INSERT INTO University.Students (id, name, age) VALUES (45, 'Name18!', 29);
