package sg.edu.nus.VTTP2022.miniproject.BookClub.service;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import sg.edu.nus.VTTP2022.miniproject.BookClub.repositories.AddBookRepositories;
import sg.edu.nus.VTTP2022.miniproject.BookClub.repositories.PostRepositories;
import sg.edu.nus.VTTP2022.miniproject.BookClub.services.SearchService;

@SpringBootTest
public class PostServiceTest {
    
    @Autowired
    private PostRepositories postRepo;

    @Autowired
    private SearchService searchSvcs;

    @Autowired
    private AddBookRepositories addBookRepo;

    // @BeforeEach
    // private void setUp() {
    //     final String SQL_INSERT_FAKE_BOOK_RECORD = "insert into books (book_id, title, infoLink, imageLink) values ('ZuKTvERuPG8C', 'Thinking, Fast and Slow', 'https://play.google.com/store/books/details?id=ZuKTvERuPG8C&source=gbs_api', 'http://books.google.com/books/content?id=ZuKTvERuPG8C&printsec=frontcover&img=1&zoom=5&edge=curl&source=gbs_api')";
    // }
}
