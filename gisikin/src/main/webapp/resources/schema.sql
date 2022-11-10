create table member (
email varchar(100) not null,
pwd varchar(1000) not null,
nick_name varchar(100) not null,
reg_at datetime default current_timestamp,
grade tinyint default '10',
primary key(email)
) default CHARSET=utf8mb4;

create table board (
bno bigint not null auto_increment,
category varchar(100) default null,
title varchar(100) not null,
writer varchar(100) not null,
description text,
reg_at datetime default current_timestamp,
mod_at datetime default current_timestamp,
read_count int default '0',
cmt_qty int default '0',
file_count int default 0,
primary key(bno)
) default CHARSET=utf8mb4;

create table comment (
cno bigint not null auto_increment,
bno bigint not null,
writer varchar(100) not null,
content varchar(2000) not null,
reg_at datetime default current_timestamp,
mod_at datetime default current_timestamp,
primary key (cno)
) default CHARSET=utf8mb4;

create table attached_file (
uuid varchar(256) primary key,
save_dir varchar(1000) not null,
file_name varchar(1000) not null,
file_type tinyint(1) default 0,
bno bigint not null,
file_size bigint not null
) default CHARSET=utf8mb4;

create table profile (
uuid varchar(256) primary key,
save_dir varchar(1000) not null,
file_name varchar(1000) not null,
file_type tinyint(1) default 0,
email varchar(100) not null,
file_size bigint not null,
) default CHARSET=utf8mb4;