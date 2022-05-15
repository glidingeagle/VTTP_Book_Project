-- drop database if exists
drop schema if exists bookclub

--create a new database
create schema bookclub;

--select database
use bookclub;

--create user table
create table users (
    user_id varchar (8) not null,
    username varchar (32) not null,
    password varchar (256) not null,
    email varchar (256) not null,

    primary key (user_id)
);

--create books table
create table books (
    book_id int not null auto_increment, 
    book_title varchar (256) not null, 
    book_authors varchar (256), 
    book_infoLink varchar (256),
    book_imageLinks varchar (256),

    primary key (book_id)
);

--create friends table
create table friends (
    
)

--create review table
create table reviews (
    review_id int not null auto_increment,
    review_status enum('Want to read', 'Currently reading', 'Read') not null,
    review_rating float,
    review_quotes varchar (256),
    review_comments varchar (256),

    --foreign keys
    user_id varchar(8),
    book_id int,

    primary key (review_id),

    constraint fk_user_id
        foreign key(user_id)
        references users(user_id),

    constraint fk_book_id
        foreign key(book_id)
        references books(book_id)
)

--Join tables
SELECT users.user_id, users.username, reviews.review_id, reviews.review_status, reviews.review_rating, reviews.review_quotes, reviews.review_comments, books.book_id, books.book_title, books.book_authors, books.book_imageLinks
FROM users
INNER JOIN reviews 
ON users.user_id = reviews.user_id
INNER JOIN books 
ON reviews.book_id = books.book_id
WHERE users.user_id = '2628f42a';
