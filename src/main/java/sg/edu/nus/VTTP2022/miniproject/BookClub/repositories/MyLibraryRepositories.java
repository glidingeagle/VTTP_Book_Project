package sg.edu.nus.VTTP2022.miniproject.BookClub.repositories;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import sg.edu.nus.VTTP2022.miniproject.BookClub.models.Book;
import sg.edu.nus.VTTP2022.miniproject.BookClub.models.Review;

import static sg.edu.nus.VTTP2022.miniproject.BookClub.repositories.Queries.*;
import static sg.edu.nus.VTTP2022.miniproject.BookClub.models.ConversionUtils.*;

@Repository
public class MyLibraryRepositories {
    
    @Autowired
    private JdbcTemplate template;

    public Optional<List<Book>> findBookReviewStatus (String user_id, int status) {
        
        List<Book> books = new LinkedList<>();
    
        SqlRowSet rs = template.queryForRowSet(SQL_GET_USER_BOOK_LIBRARY_FOR_DIFF_STATUS, user_id, status);

        // if(!rs.next()) {
        //     return Optional.empty();
        // }

        while (rs.next()) {
            Book book = convertBook(rs);
            System.out.println(book);
            books.add(book);
        }
        return Optional.of(books);
    }

    public Review findBooksAndReviewsByUserId (String user_id, String book_id) {

        Review review = new Review();

        SqlRowSet rs = template.queryForRowSet(SQL_GET_REVIEW_BY_USERID_AND_BOOKID, user_id, book_id);
        
        // if(!rs.next())
        //     return review;

        while(rs.next()) {
            review = convertReview(rs);
        }

        return review;
    }

    public Book findBookByBookId (String book_id) {

        Book book = new Book();

        SqlRowSet rs = template.queryForRowSet(SQL_GET_BOOK_BY_BOOKID, book_id);

        // if(!rs.next())
        //     return book;

        while(rs.next()) {
            book.setBook_id(rs.getString("book_id"));
            book.setTitle(rs.getString("title"));
            book.setImageLink(rs.getString("imageLink"));
            book.setInfoLink(rs.getString("infoLink"));
        }

        return book;
    }
}
