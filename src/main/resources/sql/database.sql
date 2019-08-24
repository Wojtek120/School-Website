create schema if not exists programming_school collate utf8_polish_ci;

create table if not exists exercise
(
	id int auto_increment
		primary key,
	title varchar(255) null,
	description text null
);

create table if not exists user_group
(
	id int auto_increment
		primary key,
	name varchar(255) null
);

create table if not exists users
(
	id int auto_increment
		primary key,
	email varchar(255) null,
	username varchar(255) null,
	password varchar(255) null,
	user_group_id int not null,
	constraint email
		unique (email),
	constraint users_ibfk_1
		foreign key (user_group_id) references user_group (id)
);

create table if not exists solution
(
    id int auto_increment
        primary key,
    created timestamp not null default CURRENT_TIMESTAMP,
    updated timestamp not null default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
    description text null,
    exercise_id int not null,
    user_id int not null,
    constraint solution_ibfk_1
        foreign key (exercise_id) references exercise (id),
    constraint solution_ibfk_2
        foreign key (user_id) references users (id)
);

create index exercise_id
	on solution (exercise_id);

create index user_id
	on solution (user_id);

create index user_group_id
	on users (user_group_id);

