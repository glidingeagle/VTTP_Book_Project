package sg.edu.nus.VTTP2022.miniproject.BookClub.models;

import java.util.UUID;

import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.util.MultiValueMap;

public class ConversionUtils {
    
    //taking user information from sign up page
    public static User convert (MultiValueMap<String, String> payload) {

        //Create and set user_id
        String user_id = UUID.randomUUID().toString().substring(0, 8);
        User user = new User();
        user.setUser_id(user_id);

        //set username
        user.setUsername(payload.getFirst("username"));
        //set password
        user.setPassword(payload.getFirst("password"));
        //set email
        user.setEmail(payload.getFirst("email"));
        return user;
    }

    //retrieve user from database
    public static User convertUser (SqlRowSet rs) {
        User user = new User();
        user.setUser_id(rs.getString("user_id"));
        user.setUsername(rs.getString("username"));
        user.setPassword(rs.getString("password"));
        user.setEmail(rs.getString("email"));
        return user;
    }

    //retrieve reviews from specific user
    public static Review convertReview (SqlRowSet rs) {
        Review review = new Review();
        review.setReview_id(rs.getInt("review_id"));
        review.setRating(rs.getFloat("rating"));
        review.setComment(rs.getString("comment"));
        return review;
    }

    //retrieve book from book_id
    public static Book convertBook (SqlRowSet rs) {
        Book book = new Book();
        book.setBook_id(rs.getString("book_id"));
        book.setTitle(rs.getString("title"));
        book.setImageLink(rs.getString("imageLink"));
        return book;
    }
}
