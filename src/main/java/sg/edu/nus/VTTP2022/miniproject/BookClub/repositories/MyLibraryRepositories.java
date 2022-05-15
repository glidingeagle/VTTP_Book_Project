package sg.edu.nus.VTTP2022.miniproject.BookClub.repositories;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import sg.edu.nus.VTTP2022.miniproject.BookClub.models.Review;

import static sg.edu.nus.VTTP2022.miniproject.BookClub.repositories.Queries.*;
import static sg.edu.nus.VTTP2022.miniproject.BookClub.models.ConversionUtils.*;

@Repository
public class MyLibraryRepositories {
    
    @Autowired
    private JdbcTemplate template;

    public Optional<List<Review>> findBooksAndReviewsByUserId (String user_id) {
        
        List<Review> reviewLists = new LinkedList<>();

        SqlRowSet rs = template.queryForRowSet(SQL_GET_USER_BOOK_LIBRARY, user_id);
        
        if(!rs.next())
            return Optional.empty();

        while(rs.next()) {
            Review review = convertReview(rs);
            reviewLists.add(review);
        }
        return Optional.of(reviewLists);
    }
}
