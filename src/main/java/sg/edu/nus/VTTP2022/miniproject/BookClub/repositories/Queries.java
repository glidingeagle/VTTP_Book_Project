package sg.edu.nus.VTTP2022.miniproject.BookClub.repositories;

public interface Queries {
    
    //User Authentication Queries
    public static final String SQL_COUNT_USER_BY_EMAIL = "select count(*) as email_count from users where email = ? and password = sha1(?)";

    //Create new users
    public static final String SQL_INSERT_NEW_USER = "insert into users (user_id, username, password, email) values (?, ?, sha1(?), ?)";

    //Find existing user in database
    public static final String SQL_FIND_USER_BY_EMAIL = "select * from users where email = ?";

    //Create myLibrary, collection of book for a specific user in homepage
    //Add book from google book API
    //for transaction
    public static final String SQL_INSERT_NEW_BOOK_INTO_COLLECTION = "insert into books (book_id, title, infoLink, imageLink) values (?, ?, ?, ?)";

    public static final String SQL_INSERT_NEW_COMMENT_FOR_A_BOOK = "insert into reviews (user_id, book_id) values (?, ?)";

    public static final String SQL_COUNT_BOOK_BY_BOOK_ID = "select count(*) as book_countbyid from books where book_id = ?";

    public static final String SQL_COUNT_REVIEW_BY_USERID_AND_BOOKID = "select count(*) as review_count from reviews where user_id = ? and book_id = ?";

    // This is for home display but not sure how will I use it so that I can display the objective of line 14
    public static final String SQL_GET_USER_BOOK_LIBRARY_FOR_DIFF_STATUS = "select books.title, books.imageLink from reviews right join books on reviews.book_id = books.book_id where reviews.user_id = ? and reviews.status = ?";
    
    //Queries below are for post
    //Write comment to a book
    public static final String SQL_INSERT_COMMENT_FOR_A_BOOK = "insert into reviews (status, rating, comment, user_id, book_id) values (?, ?, ?, ?, ?)";
    
    //Update comment to a book
    public static final String SQL_UPDATE_STATUS_TO_REVIEW = "update reviews set status = ?, rating = ?, comment = ? where user_id = ? and book_id = ?";

}
