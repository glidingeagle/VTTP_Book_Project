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
public class SearchAndAddControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JdbcTemplate template;

    @Test
    public void getSearchTestOneWithNoUser() {
        RequestBuilder req = MockMvcRequestBuilders.get("/protected/search").accept(MediaType.TEXT_HTML_VALUE);

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
    public void getSearchTestWithCorrectUser() {
        RequestBuilder req = MockMvcRequestBuilders
                .get("/protected/search")
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
    public void getSearchQueryTestWithCorrectUser() {

        RequestBuilder req = MockMvcRequestBuilders
                .get("/protected/search/searchResult")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .queryParam("q", "Dan Brown")
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
            assertTrue(payload.contains("Dan Brown"));
            assertNotNull(payload);
        } catch (Exception ex) {
            fail("cannot retrieve response payload", ex);
            return;
        }
    }

    @Nested
    @DisplayName ("Test for adding books and create review")
    class testingScenarioOne {

        @BeforeEach
        public void deleteFakeUserScenarioOne() {
            template.execute("delete from reviews where book_id = 'g2MBEAAAQBAJ'");
            template.execute("delete from books where book_id = 'g2MBEAAAQBAJ'");
        }

        @AfterEach
        public void deleteFakeUserScenarioOneAfter() {
            template.execute("delete from reviews where book_id = 'g2MBEAAAQBAJ'");
            template.execute("delete from books where book_id = 'g2MBEAAAQBAJ'");
        }

        @Test
        @DisplayName ("Test for adding books and create review")
        public void addingBookToLibraryScenarioOne() {

            MultiValueMap<String,String> params = new LinkedMultiValueMap<>();
            params.add("book_id", "g2MBEAAAQBAJ");

            RequestBuilder req = MockMvcRequestBuilders.post("/protected/search/searchResult/added")
                    .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                    .params(params)
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
                assertTrue(payload.contains("Added successfully!"));
                assertNotNull(payload);
            } catch (Exception ex) {
                fail("cannot retrieve response payload", ex);
                return;
            }
        }
    }

}
