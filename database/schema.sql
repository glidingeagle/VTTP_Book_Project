-- drop database if exists
drop schema if exists bookclub

--create a new database
create schema bookclub;

--select database
use bookclub;

--create user table
create table user (
    user_id char (8) not null,
    username varchar (128) not null,
    name varchar (128),

    primary key (user_id)
);

--create post table
create table post (
    book_id int not null auto_increment, 
    book_title varchar (128) not null, 
    author varchar (128) not null, 
    rating 
    comments
);