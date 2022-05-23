package sg.edu.nus.VTTP2022.miniproject.BookClub.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import sg.edu.nus.VTTP2022.miniproject.BookClub.services.UsersAuthService;

@SpringBootTest
public class UsersAuthServiceTest {
    
    @Autowired
    private UsersAuthService userAuthSvc;


    @Test
    public void authenticationPassedTest() {
        String email = "fred@hotmail.com";
        String password = "fred";
        boolean existed = userAuthSvc.authentication(email, password);
        assertTrue(existed);
    }

    // @Test
    // public void enterNewUserFailedTest() {
    //     User userOne = new User ("dividson@hotmail.com", "dividson");

    //     userAuthSvc.enterNewUser(userOne);
    //     assertFalse(inexistence);
    // }
}
