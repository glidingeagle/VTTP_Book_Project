package sg.edu.nus.VTTP2022.miniproject.BookClub.controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@AutoConfigureMockMvc (addFilters = false)
@SpringBootTest
public class MyLibraryControllerTest {
    
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JdbcTemplate template;

    @Test
    public void getHomeTestWithNoUser () {
        RequestBuilder req = MockMvcRequestBuilders.get("/protected/home").accept(MediaType.TEXT_HTML_VALUE);

        MvcResult result = null;
        try {
            result = mockMvc.perform(req).andReturn();
        } catch (Exception ex) {
            fail("cannot perform mvc invocation", ex);
            return;
        }

        MockHttpServletResponse resp = result.getResponse();
        try {
            String payload = resp.getContentAsString();
            assertNotNull(payload);
        } catch (Exception ex) {
            fail("cannot retrieve response payload", ex);
            return;
        }
    }

    @Test
    public void getPostTestWithCorrectUser () {
        RequestBuilder req = MockMvcRequestBuilders
                .get("/protected/home")
                .sessionAttr("email", "wilma@hotmail.com");

        MvcResult result = null;
        try {
            result = mockMvc.perform(req).andReturn();
        } catch (Exception ex) {
            fail("cannot perform mvc invocation", ex);
            return;
        }

        MockHttpServletResponse resp = result.getResponse();
        try {
            String payload = resp.getContentAsString();
            assertNotNull(payload);
        } catch (Exception ex) {
            fail("cannot retrieve response payload", ex);
            return;
        }
    }

    @Nested
    @DisplayName ("Test for getting a book detail and review from a particular book")
    class testingScenarioOne {

        @BeforeEach
        public void createFakeBookAndReviewForWilma() {
            template.execute("insert into books (book_id, title, infoLink, imageLink) values ('fxHaDwAAQBAJ', 'Artificial Intelligence in Medicine', 'https://play.google.com/store/books/details?id=fxHaDwAAQBAJ&source=gbs_api', 'http://books.google.com/books/content?id=fxHaDwAAQBAJ&printsec=frontcover&img=1&zoom=5&edge=curl&source=gbs_api')");
            template.execute("insert into reviews (status, user_id, book_id) values (3, '2628f42a', 'fxHaDwAAQBAJ')");
        }

        @AfterEach
        public void deleteFakeBookAndReviewForWilma() {
            template.execute("delete from reviews where book_id = 'fxHaDwAAQBAJ' and user_id='2628f42a'");
            template.execute("delete from books where book_id = 'fxHaDwAAQBAJ'");
        }

        @Test
        @DisplayName ("Test for getting a book detail and review from a particular book")
        public void getMyLibraryBookReviewWithCorrectUser () {

            String book_id = "fxHaDwAAQBAJ";

            RequestBuilder req = MockMvcRequestBuilders
                    .get("/protected/home/" + book_id)
                    .sessionAttr("email", "wilma@hotmail.com");

            MvcResult result = null;
            try {
                result = mockMvc.perform(req).andReturn();
            } catch (Exception ex) {
                fail("cannot perform mvc invocation", ex);
                return;
            }

            MockHttpServletResponse resp = result.getResponse();
            try {
                String payload = resp.getContentAsString();
                assertNotNull(payload);
            } catch (Exception ex) {
                fail("cannot retrieve response payload", ex);
                return;
            }
        }

        @Test
        public void getMyLibraryBookReviewWithNoUser () {

            String book_id = "fxHaDwAAQBAJ";

            RequestBuilder req = MockMvcRequestBuilders
                    .get("/protected/home/" + book_id)
                    .accept(MediaType.TEXT_HTML_VALUE);

            MvcResult result = null;
            try {
                result = mockMvc.perform(req).andReturn();
            } catch (Exception ex) {
                fail("cannot perform mvc invocation", ex);
                return;
            }

            MockHttpServletResponse resp = result.getResponse();
            try {
                String payload = resp.getContentAsString();
                assertNotNull(payload);
            } catch (Exception ex) {
                fail("cannot retrieve response payload", ex);
                return;
            }
        }
    }
}
