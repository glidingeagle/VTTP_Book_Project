package sg.edu.nus.VTTP2022.miniproject.BookClub.repositories;

public interface Queries {
    
    //User Authentication Queries
    public static final String SQL_COUNT_USER_BY_EMAIL = "select count(*) as email_count from users where email = ? and password = sha1(?)";

    //Create new users
    public static final String SQL_INSERT_NEW_USER = "insert into users (user_id, username, password, email) values (?, ?, sha1(?), ?)";

    //Find existing user in database
    public static final String SQL_FIND_USER_BY_EMAIL = "select * from users where email = ?";

    //Create myLibrary for a specific user in homepage
    // public static final String SQL_GET_USER_BOOK_LIBRARY = "select users.user_id, users.username, reviews.review_id, reviews.review_status, reviews.review_rating, reviews.review_quotes, reviews.review_comments, books.book_id, books.book_title, books.book_authors, books.book_imageLinks from users inner join reviews on users.user_id = reviews.user_id inner join books on reviews.book_id = books.book_id where users.user_id = ?";
    public static final String SQL_GET_USER_BOOK_LIBRARY = "select * from reviews where user_id = ?";

    //for transaction
    //need to check whether books are already in the database
    public static final String SQL_COUNT_BOOK_BY_TITLE = "select count(*) as title_count from books where book_title = ?";
    //Insert status on books into myLibrary
    public static final String SQL_INSERT_NEW_BOOK_STATUS = "insert into reviews (review_status, review_rating, review_quotes, review_comments, user_id, book_id) values (?, ?, ?, ?, ?, ?)";
    //Insert new books into myLibrary
    public static final String SQL_INSERT_NEW_BOOK_COLLECTION = "insert into books (book_title, book_authors, book_infoLink, book_imageLinks) values (?, ?, ?, ?)";
}
