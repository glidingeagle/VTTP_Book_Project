-- insert some random users into user table
insert into users (username, password, email)
    values
        ('2628f42a', 'fred', sha1('fred'), 'fred@hotmail.com'),
        ('3a308b4c', 'betty', sha1('betty'), 'betty@hotmail.com'),
        ('7d40823b', 'wilma', sha1('wilma'), 'wilma@hotmail.com');

-- insert some random books into books
insert into books (book_title, book_authors, book_infoLink, book_imageLinks)
    values
        ('The Subtle Art of Not Giving a F*ck', 'Mark Manson', 'https://play.google.com/store/books/details?id=yng_CwAAQBAJ&source=gbs_api', 'http://books.google.com/books/content?id=yng_CwAAQBAJ&printsec=frontcover&img=1&zoom=5&edge=curl&source=gbs_api'),
        ('Deception Point', 'Dan Brown', 'http://books.google.com.sg/books?id=eCWgDwAAQBAJ&dq=deception+point&hl=&source=gbs_api', 'http://books.google.com/books/content?id=eCWgDwAAQBAJ&printsec=frontcover&img=1&zoom=5&edge=curl&source=gbs_api'),
        ('The Tipping Point', 'Malcolm Gladwell', 'http://books.google.com/books/content?id=C1jkoAEACAAJ&printsec=frontcover&img=1&zoom=5&source=gbs_api', 'http://books.google.com.sg/books?id=C1jkoAEACAAJ&dq=the+tipping+point&hl=&source=gbs_api');

--insert some random comments
insert into reviews (review_status, review_rating, review_quotes, review_comments, user_id, book_id)
    values 
        ('Read', 4, 'Everything worthwhile in life is won through surmounting the associated negative experience.', null, '2628f42a', 1),
        ('Currently reading', null, null, null, '2628f42a', 2),
        ('Want to read', null, null, null, '2628f42a', 3)

