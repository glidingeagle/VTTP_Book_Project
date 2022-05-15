package sg.edu.nus.VTTP2022.miniproject.BookClub.controllers;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import sg.edu.nus.VTTP2022.miniproject.BookClub.models.Review;
import sg.edu.nus.VTTP2022.miniproject.BookClub.models.User;
import sg.edu.nus.VTTP2022.miniproject.BookClub.services.MyLibraryService;

@Controller
@RequestMapping (path="/protected")
public class ProtectedController {

    @Autowired
    private MyLibraryService myLibSvcs;

    @GetMapping (path="/{viewPage}")
    // @PostMapping (path="/protected/")
    public ModelAndView post (@PathVariable String viewPage, HttpSession session) {

        String email = (String)session.getAttribute("email");
        System.out.printf(">>> email: %s\n", email);

        User user = myLibSvcs.getUser(email);
        String user_id = user.getUser_id();
        String username = user.getUsername();

        List<Review> reviewList = myLibSvcs.getUserLibrary(user_id);
        if(reviewList != null) {
            for (int i=0; i < reviewList.size(); i++) {
                Review review = new Review();
                review.getReview_status();
                review.getReview_rating();
                review.getBook_id();
            }
        }
            


        System.out.printf(">>> viewPage: %s\n", viewPage);
        System.out.printf(">>> username: %s\n", username);

        
        

        ModelAndView mvc = new ModelAndView();
        mvc.setViewName(viewPage);
        mvc.addObject("username", user.getUsername());
        mvc.setStatus(HttpStatus.OK);

        return mvc;
    }
}
