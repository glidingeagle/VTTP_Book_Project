package sg.edu.nus.VTTP2022.miniproject.BookClub.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import sg.edu.nus.VTTP2022.miniproject.BookClub.models.Book;
import sg.edu.nus.VTTP2022.miniproject.BookClub.repositories.AddBookRepositories;
import sg.edu.nus.VTTP2022.miniproject.BookClub.repositories.PostRepositories;
import sg.edu.nus.VTTP2022.miniproject.BookClub.services.PostService;
import sg.edu.nus.VTTP2022.miniproject.BookClub.services.SearchService;

@SpringBootTest
public class PostServiceTest {
    
    @Autowired
    private PostService postSvcs;

    @Autowired
    private AddBookRepositories addBookRepo;

    // public MultiValueMap<String, String> randomMultiValueMapForm() {
    //     MultiValueMap<String, String> parameters = new LinkedMultiValueMap<String, String>();
    //     parameters.add("user_id", "2628f42a");
    //     parameters.add("book_id", "OH9vAwAAQBAJ");
    //     parameters.add("status", "2");
    //     parameters.add("rating", "0");
    //     parameters.add("comment", null);

    //     return parameters;
    // }

    // @Test
    // public void insertNewPostForReviewTest() {

    //     Book book = new Book ();
    //     book.setBook_id("OH9vAwAAQBAJ");
    //     book.setTitle("Python for Finance");
    //     book.setInfoLink("https://play.google.com/store/books/details?id=OH9vAwAAQBAJ&source=gbs_api");
    //     book.setImageLink("http://books.google.com/books/content?id=OH9vAwAAQBAJ&printsec=frontcover&img=1&zoom=5&edge=curl&source=gbs_api");

    //     addBookRepo.insertBookRecord(book);

    //     // String user_id = "2628f42a";
    //     // String book_id = "OH9vAwAAQBAJ";

    //     try {
    //         postSvcs.addPost(randomMultiValueMapForm());
    //         assertEquals(1, addBookRepo.countReviewByUserAndBook("2628f42a", "OH9vAwAAQBAJ"));
    //     } catch (Exception excep) {
    //         excep.printStackTrace();
    //     }
        
    // }

    // @Test
    // public void updateExistingPostTest() {

    //     try {
    //         postSvcs.addPost(randomMultiValueMapForm());
    //         assertTrue();
    //     } catch (Exception excep) {
    //         excep.printStackTrace();
    //     }
        
    // }

    // @BeforeEach
    // private void setUp() {
    //     final String SQL_INSERT_FAKE_BOOK_RECORD = "insert into books (book_id, title, infoLink, imageLink) values ('ZuKTvERuPG8C', 'Thinking, Fast and Slow', 'https://play.google.com/store/books/details?id=ZuKTvERuPG8C&source=gbs_api', 'http://books.google.com/books/content?id=ZuKTvERuPG8C&printsec=frontcover&img=1&zoom=5&edge=curl&source=gbs_api')";
    // }
}
