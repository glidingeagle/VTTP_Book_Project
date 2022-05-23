package sg.edu.nus.VTTP2022.miniproject.BookClub.controller;

import static org.junit.jupiter.api.Assertions.*;

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
    public void getSearchTest() {
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
    public void getSearchQueryTest() {

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

    // @Test
    // public void addingBookToLibraryTest() {
    //     RequestBuilder req = MockMvcRequestBuilders
    //             .post("/protected/search/searchResult/added")
    //             .accept(MediaType.APPLICATION_FORM_URLENCODED)
    //             .queryParam("book_id", "ZuKTvERuPG8C")
    //             .sessionAttr("email", "wilma@hotmail.com");

    //     MvcResult result = null;
    //     try {
    //         result = mockMvc.perform(req).andReturn();
    //     } catch (Exception ex) {
    //         fail("cannot perform mvc invocation", ex);
    //         return;
    //     }

    //     MockHttpServletResponse resp = result.getResponse();
    //     try {
    //         String payload = resp.getContentAsString();
    //         assertTrue(payload.contains("Added successfully!"));
    //         assertNotNull(payload);
    //     } catch (Exception ex) {
    //         fail("cannot retrieve response payload", ex);
    //         return;
    //     }
    // }

}
