package sg.edu.nus.VTTP2022.miniproject.BookClub.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import sg.edu.nus.VTTP2022.miniproject.BookClub.repositories.AddBookRepositories;
import sg.edu.nus.VTTP2022.miniproject.BookClub.services.AddBookService;

@SpringBootTest
public class AddServiceTest {
    
    @Autowired
    private AddBookService addBookSvcs;

    @Autowired
    private AddBookRepositories addBookRepo;

    @Autowired
    private JdbcTemplate template;

    @AfterEach
    public void deleteReviewRecords() {
        template.execute("delete from reviews where user_id = '2628f42a' and book_id = 'yng_CwAAQBAJ'");
    }

    public MultiValueMap<String, String> randomMultiValueMapFormNumberOne() {
        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<String, String>();
        parameters.add("user_id", "2628f42a");
        parameters.add("book_id", "yng_CwAAQBAJ");

        return parameters;
    }

    @Test
    public void addAPostWhenAddingBookTest() {

        String user_id = "2628f42a";
        String book_id = "yng_CwAAQBAJ";

        try {
            addBookSvcs.addBookAndReview(user_id, book_id);
            assertEquals(1, addBookRepo.countReviewByUserAndBook("2628f42a", "yng_CwAAQBAJ"));
        } catch (Exception excep) {
            excep.printStackTrace();
        }
        
    }
    
}
