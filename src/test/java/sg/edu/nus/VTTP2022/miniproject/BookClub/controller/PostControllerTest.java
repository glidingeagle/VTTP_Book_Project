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
public class PostControllerTest {
    
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JdbcTemplate template;

    @Test
    public void getPostTestWithNoUser () {
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

    @Test
    public void getPostTestWithCorrectUser () {
        RequestBuilder req = MockMvcRequestBuilders
                .get("/protected/post")
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
    @DisplayName ("Test for scenario one")
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
    
    // @Nested
    // @DisplayName ("Test for scenario two")
    // class testingScenarioTwo {

    //     @AfterEach
    //     public void deleteReviewRecords() {
    //         template.execute("delete from reviews where user_id = '2628f42a' and book_id = 'yng_CwAAQBAJ'");
    //     }

    //     @Test
    //     @DisplayName ("Test for scenario two")
    //     public void writeAPostTestScenarioTwo () {

    //         MultiValueMap<String,String> params = new LinkedMultiValueMap<>();
    //         params.add("status", "2");
    //         params.add("rating", null);
    //         params.add("comment", null);
    //         params.add("user_id", "2628f42a");
    //         params.add("book_id", "yng_CwAAQBAJ");
    
    //         RequestBuilder req = MockMvcRequestBuilders.post("/protected/post/posted")
    //                 .contentType(MediaType.APPLICATION_FORM_URLENCODED)
    //                 .params(params)
    //                 .sessionAttr("email", "wilma@hotmail.com");
    
    //         MvcResult result = null;
    //         try {
    //             result = mockMvc.perform(req).andReturn();
    //         } catch (Exception ex) {
    //             fail("cannot perform mvc invocation", ex);
    //             return;
    //         }
    
    //         MockHttpServletResponse resp = result.getResponse();
    //         try {
    //             String payload = resp.getContentAsString();
    //             assertTrue(payload.contains("Posted successfully!"));
    //             assertNotNull(payload);
    //         } catch (Exception ex) {
    //             fail("cannot retrieve response payload", ex);
    //             return;
    //         }
    //     }
    // }
   
    @Nested
    @DisplayName ("Test for scenario three")
    class testingScenarioThree {

        @BeforeEach
        public void createFakeBookAndUserRecord() {
            template.execute("insert into books (book_id, title, infoLink, imageLink) values ('9eL7DwAAQBAJ', 'Advances in Clean Energy Technologies', 'http://books.google.com/books/content?id=9eL7DwAAQBAJ&printsec=frontcover&img=1&zoom=5&edge=curl&source=gbs_api', 'https://play.google.com/store/books/details?id=9eL7DwAAQBAJ&source=gbs_api')");
            template.execute("insert into reviews (status, rating, comment, user_id, book_id) values (1, null, null, '2628f42a', '9eL7DwAAQBAJ')");
        }


        @AfterEach
        public void deleteFakeBookAndUserRecord() {
            template.execute("delete from reviews where book_id = '9eL7DwAAQBAJ'");
            template.execute("delete from books where book_id = '9eL7DwAAQBAJ'");
        }

        @Test
        @DisplayName ("Test for scenario three")
        public void writeAPostTestScenarioThree () {

            MultiValueMap<String,String> params = new LinkedMultiValueMap<>();
            params.add("user_id", "2628f42a");
            params.add("book_id", "9eL7DwAAQBAJ");
            params.add("status", "3");
            params.add("rating", "4");
            params.add("comment", "Wow!");
    
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
    public void getPostedTestWithNoUser() {
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

    @Test
    public void getPostedTestWithCorrectUser() {
        RequestBuilder req = MockMvcRequestBuilders
                .get("/protected/post/posted")
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
}
