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
        review.setReview_status(rs.getInt("review_status"));
        review.setReview_quotes(rs.getString("review_quotes"));
        review.setReview_comments(rs.getString("review_comments"));
        review.setUser_id(rs.getString("user_id"));
        review.setBook_id(rs.getInt("book_id"));
        return review;
    }
}
