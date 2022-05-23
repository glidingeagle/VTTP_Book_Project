package sg.edu.nus.VTTP2022.miniproject.BookClub.controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
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
public class PostControllerTest {
    
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JdbcTemplate template;

    @Test
    public void getPostTest () {
        RequestBuilder req = MockMvcRequestBuilders.get("/protected/post").accept(MediaType.TEXT_HTML_VALUE);

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
    @DisplayName ("Test for scenario one")
    class testingScenarioOne {

        @AfterEach
        public void deleteFakeUserScenarioOne() {
            template.execute("delete from reviews where book_id = 'g2MBEAAAQBAJ'");
            template.execute("delete from books where book_id = 'g2MBEAAAQBAJ'");
        }

        @Test
        @DisplayName ("Test for scenario one")
        public void writeAPostTestScenarioOne() {

            MultiValueMap<String,String> params = new LinkedMultiValueMap<>();
            params.add("user_id", "2628f42a");
            params.add("book_id", "g2MBEAAAQBAJ");
            params.add("status", "1");
            params.add("rating", "4");
            params.add("comment", "It is a good read.");

            RequestBuilder req = MockMvcRequestBuilders.post("/protected/post/posted")
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
                assertTrue(payload.contains("Posted successfully!"));
                assertNotNull(payload);
            } catch (Exception ex) {
                fail("cannot retrieve response payload", ex);
                return;
            }
        }
    }
    
    @Nested
    @DisplayName ("Test for scenario two")
    class testingScenarioTwo {
        @AfterEach
        public void deleteFakeUserScenarioTwo() {
            template.execute("delete from reviews where book_id = 'ZuKTvERuPG8C'");
        }

        public void writeAPostTestScenarioTwo () {

            MultiValueMap<String,String> params = new LinkedMultiValueMap<>();
            params.add("user_id", "2628f42a");
            params.add("book_id", "ZuKTvERuPG8C");
            params.add("status", "3");
            params.add("rating", null);
            params.add("comment", null);
    
            RequestBuilder req = MockMvcRequestBuilders.post("/protected/post/posted")
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
                assertTrue(payload.contains("Posted successfully!"));
                assertNotNull(payload);
            } catch (Exception ex) {
                fail("cannot retrieve response payload", ex);
                return;
            }
        }
    
    }
   
    @Nested
    @DisplayName ("Test for scenario three")
    class testingScenarioThree {
        @AfterEach
        public void deleteFakeUserScenarioThree() {
            template.execute("update reviews set status = '1' , rating = null, comment = null where user_id = '2628f42a' and book_id = 'FmyBAwAAQBAJ'");
        }

        public void writeAPostTestScenarioThree () {

            MultiValueMap<String,String> params = new LinkedMultiValueMap<>();
            params.add("user_id", "2628f42a");
            params.add("book_id", "FmyBAwAAQBAJ");
            params.add("status", "1");
            params.add("rating", "4");
            params.add("comment", "One of the most informative and interesting books I have ever read");
    
            RequestBuilder req = MockMvcRequestBuilders.post("/protected/post/posted")
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
                assertTrue(payload.contains("Posted successfully!"));
                assertNotNull(payload);
            } catch (Exception ex) {
                fail("cannot retrieve response payload", ex);
                return;
            }
        }
    
    }

    @Test
    public void getPostedTest() {
        RequestBuilder req = MockMvcRequestBuilders.get("/protected/post/posted").accept(MediaType.TEXT_HTML_VALUE);

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
