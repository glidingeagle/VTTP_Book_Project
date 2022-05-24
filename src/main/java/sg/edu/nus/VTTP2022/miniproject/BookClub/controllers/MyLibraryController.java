package sg.edu.nus.VTTP2022.miniproject.BookClub.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import sg.edu.nus.VTTP2022.miniproject.BookClub.models.Book;
import sg.edu.nus.VTTP2022.miniproject.BookClub.models.Review;
import sg.edu.nus.VTTP2022.miniproject.BookClub.models.User;
import sg.edu.nus.VTTP2022.miniproject.BookClub.services.MyLibraryService;


@Controller
@RequestMapping (path="/protected")
public class MyLibraryController {

    @Autowired
    private MyLibraryService myLibSvcs;

    @GetMapping(path="/home")
    public ModelAndView getHome (HttpSession session) {

        String email = (String)session.getAttribute("email");
        System.out.printf(">>> email: %s\n", email);

        User user = myLibSvcs.getUser(email);
        String user_id = user.getUser_id();
        String username = user.getUsername();

        List<Book> wantToRead = myLibSvcs.getWantToReadBook(user_id, 1);

        List<Book> currentlyReading = myLibSvcs.getCurrentlyReadingBook(user_id, 2);

        List<Book> read = myLibSvcs.getReadBook(user_id, 3);

        
        // List<Review> reviewList = myLibSvcs.getUserLibrary(user_id);
        // Review review = new Review();
        // if(reviewList != null) {
        //     for (int i=0; i < reviewList.size(); i++) {
        //         if(review.getStatus() == 3) {
                    
        //         }
        //         //review.getReview_id();
        //         review.getStatus();
        //         //review.getReview_rating();
        //         //review.getReview_quotes();
        //         //review.getReview_comments();
        //         review.getBook_id();
        //     }
        // }

        //System.out.printf(">>> viewPage: %s\n", viewPage);
        System.out.printf(">>> username: %s\n", username);
        // System.out.printf(">>> username: %s\n", review);
        System.out.printf(">>> wantToRead: %s\n", wantToRead.toString());
        System.out.printf(">>> currentlyReading: %s\n", currentlyReading.toString());
        System.out.printf(">>> read: %s\n", read.toString());

        ModelAndView mvc = new ModelAndView();
        if(session.getAttribute("email") == null) {
            mvc.setViewName("redirect:/");
            return mvc;
        }

        mvc.setViewName("home");
        mvc.addObject("username", user.getUsername());
        mvc.addObject("user_id", user.getUser_id());
        mvc.addObject("wantToRead", wantToRead);
        mvc.addObject("currentlyReading", currentlyReading);
        mvc.addObject("read", read);
        mvc.setStatus(HttpStatus.OK);

        return mvc;
    }

    @GetMapping (path="/home/{book_id}")
    public ModelAndView getMyLibraryBookReview (@PathVariable (value="book_id") String book_id, HttpSession session) {
        ModelAndView mvc = new ModelAndView();
        if(session.getAttribute("email") == null) {
            mvc.setViewName("redirect:/");
            return mvc;
        }

        String email = (String)session.getAttribute("email");
        System.out.printf(">>> email: %s\n", email);

        User user = myLibSvcs.getUser(email);
        String user_id = user.getUser_id();
        String username = user.getUsername();

        System.out.println(username);
        System.out.println(book_id);

        Review review = myLibSvcs.getUserLibraryOneReview(user_id, book_id);
        Book book = myLibSvcs.getUserLibrarySelectedBook(book_id);
        System.out.println(review);
        System.out.println(book);
        mvc.setViewName("bookReview");
        mvc.addObject("review", review);
        mvc.addObject("book", book);
        mvc.setStatus(HttpStatus.OK);

        return mvc;
    }

}
