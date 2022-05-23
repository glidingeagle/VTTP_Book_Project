package sg.edu.nus.VTTP2022.miniproject.BookClub.controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
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
public class UsersAuthControllerTest {
    
    @Autowired
    private JdbcTemplate template;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void viewLoginTest() {
        RequestBuilder req = MockMvcRequestBuilders.get("/").accept(MediaType.TEXT_HTML_VALUE);

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
    public void viewSignupTest() {
        RequestBuilder req = MockMvcRequestBuilders.get("/signup").accept(MediaType.TEXT_HTML_VALUE);

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
    public void getNewUserFailedTestByExistingEmail() {

        MultiValueMap<String,String> params = new LinkedMultiValueMap<>();
        params.add("user_id", "2628f42a");
        params.add("username", "wilma");
        params.add("password", "wilma");
        params.add("email", "wilma@hotmail.com");

        RequestBuilder req = MockMvcRequestBuilders.post("/index")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .params(params);

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
            assertTrue(payload.contains("Sorry! User has already been registered."));
            assertNotNull(payload);
        } catch (Exception ex) {
            fail("cannot retrieve response payload", ex);
            return;
        }
    }

    @Test
    public void getNewUserPassedTest() {

        MultiValueMap<String,String> params = new LinkedMultiValueMap<>();
        params.add("username", "dividson");
        params.add("password", "dividson");
        params.add("email", "dividson@hotmail.com");

        RequestBuilder req = MockMvcRequestBuilders.post("/index")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .params(params);

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


    @AfterEach
    public void deleteFakeUser() {
        template.execute("delete from users where username = 'dividson'");
    }

    @Test
    public void viewGetLogoutTest() {
        RequestBuilder req = MockMvcRequestBuilders.get("/logout").accept(MediaType.TEXT_HTML_VALUE);

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
    public void postIndexFailedTest() {
        MultiValueMap<String,String> params = new LinkedMultiValueMap<>();
        params.add("email", "steve@hotmail.com");
        params.add("password", "steve");

        RequestBuilder req = MockMvcRequestBuilders.post("/home")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .params(params)
                .sessionAttr("email", "steve@hotmail.com");

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
            assertTrue(payload.contains("Log in failed! Check with administrator."));
            assertNotNull(payload);
        } catch (Exception ex) {
            fail("cannot retrieve response payload", ex);
            return;
        }
    }

    @Test
    public void postIndexPassedTest() {
        MultiValueMap<String,String> params = new LinkedMultiValueMap<>();
        params.add("email", "wilma@hotmail.com");
        params.add("password", "wilma");

        RequestBuilder req = MockMvcRequestBuilders.post("/home")
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
            assertNotNull(payload);
        } catch (Exception ex) {
            fail("cannot retrieve response payload", ex);
            return;
        }
        
    }


    // below codes also works
    // @Autowired
    // private WebApplicationContext context;

    // @BeforeEach
    // private void setUp() {
    //     mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    // }

    // @Test
    // public void viewLoginTest() throws Exception {

    //     MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/")).andReturn();

    //     ModelAndView mav = mvcResult.getModelAndView();

    //     ModelAndViewAssert.assertViewName(mav, "index");
    // }

    // @Test
    // public void viewSignupTest() throws Exception {

    //     MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/signup")).andReturn();

    //     ModelAndView mav = mvcResult.getModelAndView();

    //     ModelAndViewAssert.assertViewName(mav, "signup");
    // }
}
