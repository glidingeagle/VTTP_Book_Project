package sg.edu.nus.VTTP2022.miniproject.BookClub.service;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import sg.edu.nus.VTTP2022.miniproject.BookClub.models.Book;
import sg.edu.nus.VTTP2022.miniproject.BookClub.services.SearchService;

@SpringBootTest
public class SearchServiceTest {
    
    @Autowired
    private SearchService searchSvcs;

    @Test
    public void shouldReturnAListOfBooks() {
        Optional<List<Book>> bookListOpt = searchSvcs.getBookSearch("Dan Brown");
        assertTrue(bookListOpt.isPresent());
    }

    @Test
    public void shouldReturnABook() {
        Book book = searchSvcs.getBookSearchById("jJaLBgAAQBAJ");
        assertTrue(book.getTitle().contains("Corporate Finance"));
    }
}
