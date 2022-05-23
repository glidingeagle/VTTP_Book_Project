package sg.edu.nus.VTTP2022.miniproject.BookClub.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import static sg.edu.nus.VTTP2022.miniproject.BookClub.repositories.Queries.*;

@Repository
public class PostRepositories {
    
    @Autowired
    private JdbcTemplate template;

    public boolean insertPostToReview (int status, float rating, String comment, String user_id, String book_id) {
        
        int count = template.update(SQL_INSERT_COMMENT_FOR_A_BOOK, status, rating, comment, user_id, book_id);

        return 1 == count;
    }

    public boolean updatePostToReview (int status, float rating, String comment, String user_id, String book_id) {

        int count = template.update(SQL_UPDATE_STATUS_TO_REVIEW, status, rating, comment, user_id, book_id);

        return 1 == count;
    }

}
