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
		(3, 4.0, 'Brimming with facts, science, mythology, and arts - this book is a worthy time investment for this alone. A style that you can expect from Dan Brown. That being said, in my opinion, the book is packed with climax that draws you in. The overall direction from the start to finish was captivating.', '3a308b4c', 'Pz4YDQAAQBAJ'),
        (3, 4.0, 'Dan Brown never fail to surprise me. He is just excellent when it comes to his thrillers. A book that is indescribable!', '3a308b4c', 'QHsF2134iY0C'),
        (3, 4.5, 'Success is not a random act. It arises out of a predictable and powerful set of circumstances and opportunities.', '3a308b4c', 'ialrgIT41OAC'),
        (3, 4.5, 'The first task of Blink is to convince you of a simple fact: decisions made very quickly can be every bit as good as decisions made cautiously and deliberately.', '3a308b4c', 'VKGbb1hg8JAC'),
        (2, null, null, '3a308b4c', '5fqbz_qGi0AC');
        (2, null, null, '3a308b4c', 'GFevO-QxQDgC');
        (1, null, null, '3a308b4c', 'ZuKTvERuPG8C');
        (1, null, null, '3a308b4c', 'FmyBAwAAQBAJ');

