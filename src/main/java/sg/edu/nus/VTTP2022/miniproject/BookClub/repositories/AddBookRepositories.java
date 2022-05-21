package sg.edu.nus.VTTP2022.miniproject.BookClub.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import sg.edu.nus.VTTP2022.miniproject.BookClub.models.Book;

import static sg.edu.nus.VTTP2022.miniproject.BookClub.repositories.Queries.*;

@Repository
public class AddBookRepositories {
    
    @Autowired
    private JdbcTemplate template;

    public boolean insertBookRecord (Book book) {

        int count = template.update(SQL_INSERT_NEW_BOOK_INTO_COLLECTION, book.getBook_id(), book.getTitle(), book.getInfoLink(), book.getImageLink());

        return 1 == count;
    }

    public boolean insertReviewRecord (String user_id, String book_id) {

        int count = template.update(SQL_INSERT_NEW_COMMENT_FOR_A_BOOK, user_id, book_id);

        return 1 == count;
    }

    public int countBookByID (String book_id) {
        SqlRowSet rs = template.queryForRowSet(SQL_COUNT_BOOK_BY_BOOK_ID, book_id);
        
        if (!rs.next())
            return 0;

        return rs.getInt("book_countbyid");
    }

    public int countReviewByUserAndBook (String user_id, String book_id) {
        SqlRowSet rs = template.queryForRowSet(SQL_COUNT_REVIEW_BY_USERID_AND_BOOKID, user_id, book_id);
        
        if (!rs.next())
            return 0;

        return rs.getInt("review_count");
    }
}
