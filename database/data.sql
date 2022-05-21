-- insert some random users into user table
insert into users (user_id, username, password, email)
    values
        ('3a308b4c', 'fred', sha1('fred'), 'fred@hotmail.com'),
        ('7d40823b', 'betty', sha1('betty'), 'betty@hotmail.com'),
        ('2628f42a', 'wilma', sha1('wilma'), 'wilma@hotmail.com');

-- insert some random books into books
insert into books (book_id, title, infoLink, imageLink)
    values
        ('yng_CwAAQBAJ', 'The Subtle Art of Not Giving a F*ck', 'https://play.google.com/store/books/details?id=yng_CwAAQBAJ&source=gbs_api', 'http://books.google.com/books/content?id=yng_CwAAQBAJ&printsec=frontcover&img=1&zoom=5&edge=curl&source=gbs_api'),
        ('eiNnSBqWvU0C', 'Deception Point', 'http://books.google.com.sg/books?id=eCWgDwAAQBAJ&dq=deception+point&hl=&source=gbs_api', 'http://books.google.com/books/content?id=eCWgDwAAQBAJ&printsec=frontcover&img=1&zoom=5&edge=curl&source=gbs_api'),
        ('yBDBEGBIUmgC', 'The Tipping Point', 'http://books.google.com/books/content?id=C1jkoAEACAAJ&printsec=frontcover&img=1&zoom=5&source=gbs_api', 'http://books.google.com.sg/books?id=C1jkoAEACAAJ&dq=the+tipping+point&hl=&source=gbs_api'),
        ('UfKpDwAAQBAJ', 'The Buddha and the Badass', 'https://play.google.com/store/books/details?id=UfKpDwAAQBAJ&source=gbs_api', 'http://books.google.com/books/content?id=UfKpDwAAQBAJ&printsec=frontcover&img=1&zoom=5&edge=curl&source=gbs_api'),
        ('G2qxDwAAQBAJ', 'Think Like a Monk', 'https://play.google.com/store/books/details?id=G2qxDwAAQBAJ&source=gbs_api', 'http://books.google.com/books/content?id=G2qxDwAAQBAJ&printsec=frontcover&img=1&zoom=5&edge=curl&source=gbs_api');

insert into reviews (status, rating, comment, user_id, book_id)
	values
		(3, 3.5, 'The book wasn\'t bad. I enjoyed it.', '3a308b4c', 'yng_CwAAQBAJ'),
        (2, null, null, '3a308b4c', 'eiNnSBqWvU0C'),
        (1, null, null, '3a308b4c', 'yBDBEGBIUmgC'),
        (3, 4, 'An awesome book. The slap on the face everyone needs it! The author has it\'s own way of writting things and it can be brutal and sound like an arrogant person, but it\'s so well written!', '7d40823b', 'eiNnSBqWvU0C'),
        (3, 4, 'Slow read but good perspective on how to build a better life for ourself.', '2628f42a', 'G2qxDwAAQBAJ');

