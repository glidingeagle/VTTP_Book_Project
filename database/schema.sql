-- drop database if exists
drop schema if exists bookclub

--create a new database
create schema bookclub;

--select database
use bookclub;

--create users table
create table users (
    user_id varchar (8) not null,
    username varchar (32) not null,
    password varchar (256) not null,
    email varchar (256) not null,

    primary key (user_id)
);

--create books table
create table books (
    book_id varchar(32) not null, 
    title varchar (256) not null,  
    infoLink varchar (256) not null,
    imageLink varchar (256) not null,

    primary key (book_id)
);

--create review table
create table reviews (
    review_id int not null auto_increment,
    status enum('Want to read', 'Currently reading', 'Read') default 'Want to read',
    rating float,
    comment varchar(512),

    --foreign keys
    user_id varchar(8),
    book_id varchar(32),

    primary key (review_id),

    constraint fk_user_id
        foreign key(user_id)
        references users(user_id),

    constraint fk_book_id
        foreign key(book_id)
        references books(book_id)
)
