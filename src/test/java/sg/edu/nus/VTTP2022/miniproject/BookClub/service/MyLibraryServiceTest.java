package sg.edu.nus.VTTP2022.miniproject.BookClub.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import sg.edu.nus.VTTP2022.miniproject.BookClub.models.Book;
import sg.edu.nus.VTTP2022.miniproject.BookClub.services.MyLibraryService;

@SpringBootTest
public class MyLibraryServiceTest {
    
    @Autowired
    private MyLibraryService myLibSvcs;

    @Autowired
    private JdbcTemplate template;

    @BeforeEach
    public void createRandomReviewRecords() {
        template.execute("insert into books (book_id, title, infoLink, imageLink) values ('zOE2DwAAQBAJ', 'Rethinking Economics', 'https://play.google.com/store/books/details?id=zOE2DwAAQBAJ&source=gbs_api', 'http://books.google.com/books/content?id=zOE2DwAAQBAJ&printsec=frontcover&img=1&zoom=5&edge=curl&source=gbs_api')");
        template.execute("insert into books (book_id, title, infoLink, imageLink) values ('F_QxEAAAQBAJ', 'Pandemic Economics', 'https://play.google.com/store/books/details?id=F_QxEAAAQBAJ&source=gbs_api', 'http://books.google.com/books/content?id=F_QxEAAAQBAJ&printsec=frontcover&img=1&zoom=5&edge=curl&source=gbs_api')");
        template.execute("insert into books (book_id, title, infoLink, imageLink) values ('DdPgDwAAQBAJ', 'Narrative Economics', 'https://play.google.com/store/books/details?id=DdPgDwAAQBAJ&source=gbs_api', 'http://books.google.com/books/content?id=DdPgDwAAQBAJ&printsec=frontcover&img=1&zoom=5&edge=curl&source=gbs_api')");
        template.execute("insert into books (book_id, title, infoLink, imageLink) values ('osqizgEACAAJ', 'Economics in Practice: Evidence-Based Policymaking in Singapore', 'http://books.google.com.sg/books?id=osqizgEACAAJ&dq=economics&hl=&source=gbs_api', 'http://books.google.com/books/content?id=osqizgEACAAJ&printsec=frontcover&img=1&zoom=5&source=gbs_api')");
        template.execute("insert into books (book_id, title, infoLink, imageLink) values ('HObsDQAAQBAJ', 'The Everything Economics Book', 'https://play.google.com/…sDQAAQBAJ&source=gbs_api', 'http://books.google.com/books/content?id=HObsDQAAQBAJ&printsec=frontcover&img=1&zoom=5&edge=curl&source=gbs_api')");
        template.execute("insert into books (book_id, title, infoLink, imageLink) values ('ILxNEAAAQBAJ', 'Doing Economics', 'http://books.google.com.sg/books?id=ILxNEAAAQBAJ&dq=economics&hl=&source=gbs_api', 'http://books.google.com/books/content?id=ILxNEAAAQBAJ&printsec=frontcover&img=1&zoom=5&edge=curl&source=gbs_api')");
        template.execute("insert into reviews (status, user_id, book_id) values (1, '2628f42a', 'zOE2DwAAQBAJ')");
        template.execute("insert into reviews (status, user_id, book_id) values (1, '2628f42a', 'F_QxEAAAQBAJ')");
        template.execute("insert into reviews (status, user_id, book_id) values (2, '2628f42a', 'DdPgDwAAQBAJ')");
        template.execute("insert into reviews (status, user_id, book_id) values (2, '2628f42a', 'osqizgEACAAJ')");
        template.execute("insert into reviews (status, user_id, book_id) values (3, '2628f42a', 'HObsDQAAQBAJ')");
        template.execute("insert into reviews (status, user_id, book_id) values (3, '2628f42a', 'ILxNEAAAQBAJ')");
    } 

    @AfterEach
    public void removeRandomReviewRecords() {
        template.execute("delete from reviews where user_id='2628f42a' and book_id='zOE2DwAAQBAJ'");
        template.execute("delete from reviews where user_id='2628f42a' and book_id='F_QxEAAAQBAJ'");
        template.execute("delete from reviews where user_id='2628f42a' and book_id='DdPgDwAAQBAJ'");
        template.execute("delete from reviews where user_id='2628f42a' and book_id='osqizgEACAAJ'");
        template.execute("delete from reviews where user_id='2628f42a' and book_id='HObsDQAAQBAJ'");
        template.execute("delete from reviews where user_id='2628f42a' and book_id='ILxNEAAAQBAJ'");
        template.execute("delete from books where book_id='zOE2DwAAQBAJ'");
        template.execute("delete from books where book_id='F_QxEAAAQBAJ'");
        template.execute("delete from books where book_id='DdPgDwAAQBAJ'");
        template.execute("delete from books where book_id='osqizgEACAAJ'");
        template.execute("delete from books where book_id='HObsDQAAQBAJ'");
        template.execute("delete from books where book_id='ILxNEAAAQBAJ'");
    } 

    @Test
    public void getWantToReadBookTest() {

        String user_id = "2628f42a";
        Integer status = 1;

        List<Book> books = myLibSvcs.getWantToReadBook(user_id, status);
        assertEquals(2, books.size());
    }

    @Test
    public void getCurrentlyReadingBookTest() {

        String user_id = "2628f42a";
        Integer status = 2;

        List<Book> books = myLibSvcs.getCurrentlyReadingBook(user_id, status);
        assertEquals(2, books.size());
    }

    @Test
    public void getReadBookTest() {

        String user_id = "2628f42a";
        Integer status = 3;

        List<Book> books = myLibSvcs.getReadBook(user_id, status);
        assertEquals(2, books.size());
    }
}
