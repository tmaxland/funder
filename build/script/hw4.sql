drop table if exists projects;
drop table if exists users;
drop table if exists rewards;
drop table if exists user_project;


create table projects(


	id					integer primary key auto_increment,
	title				varchar(255),
	description			varchar(8000),
	creator_id			integer references users(id),
	funding_target		integer,
	start_date			date,
	funding_duration	integer

);



create table users(

	id			integer primary key auto_increment,
	username	varchar(255),
	password	varchar(255),
	first_name 	varchar(255),
	last_name 	varchar(255),
	email 		varchar(255)
);


create table rewards(

	id 					integer primary key auto_increment,
	amount 				integer,
	description			varchar(8000),
	project_id			integer references projects(id)


);



create table user_project (
	user_id				integer references users(id),
	project_id			integer references projects(id),
	pledge_amount		integer
);


insert into projects (id, title, description , creator_id, 
funding_target, start_date, funding_duration) 
values (1, 'Google', 'A search Engine for the masses', 1, 5000, '2013/03/06', 10);


insert into projects (id, title, description , creator_id, 
funding_target, start_date, funding_duration) 
values (2, 'facebook', 'A Social network for the masses', 2, 7000, '2013/03/20', 45);

insert into projects (id, title, description , creator_id, 
funding_target, start_date, funding_duration) 
values (3, 'Wikipedia', 'An online encyclopedia for the masses', 3, 9000, 
'2013/03/24', 66);


insert into users (id, username, password) values (1,'tali','abcd');
insert into users (id, username, password) values (2,'cysun','abcd');
insert into users (id, username, password) values (3,'jimmy','abcd');
insert into users (id, username, password) values (4,'cs320stu31','abcd');


insert into rewards (id, amount, description, project_id) values 
(1,5,'A free Google Mug',1);
insert into rewards (id, amount, description, project_id) values 
(2,10,'A free Google T-Shirt',1);
insert into rewards (id, amount, description, project_id) values 
(3,15,'A free Google Calender',1);

insert into rewards (id, amount, description, project_id) values 
(4,10,'A free Facebok Mug',2);
insert into rewards (id, amount, description, project_id) values 
(5,25,'A free Facebook T-Shirt',2);
insert into rewards (id, amount, description, project_id) values 
(6,30,'A free Facebook Calender',2);

insert into rewards (id, amount, description, project_id) values 
(7,30,'A free Wikipedia Mug',3);
insert into rewards (id, amount, description, project_id) values 
(8,50,'A free Wikipedia T-Shirt',3);
insert into rewards (id, amount, description, project_id) values 
(9,100,'A free Wikipedia Calender',3);

