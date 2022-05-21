package sg.edu.nus.VTTP2022.miniproject.BookClub.controllers;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import sg.edu.nus.VTTP2022.miniproject.BookClub.models.Book;
import sg.edu.nus.VTTP2022.miniproject.BookClub.models.Review;
import sg.edu.nus.VTTP2022.miniproject.BookClub.models.User;
import sg.edu.nus.VTTP2022.miniproject.BookClub.services.AddBookService;
import sg.edu.nus.VTTP2022.miniproject.BookClub.services.BookAndReviewExistedException;
import sg.edu.nus.VTTP2022.miniproject.BookClub.services.MyLibraryService;
import sg.edu.nus.VTTP2022.miniproject.BookClub.services.PostService;
import sg.edu.nus.VTTP2022.miniproject.BookClub.services.SearchService;

@Controller
@RequestMapping (path="/protected")
public class ProtectedController {

    @Autowired
    private MyLibraryService myLibSvcs;

    @Autowired
    private SearchService searchSvcs;

    @Autowired
    private AddBookService addBookSvcs;

    @Autowired
    private PostService postSvcs;

    @GetMapping(path="/home")
    //@PostMapping //(path="/protected/")
    public ModelAndView post (HttpSession session) {

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

    @GetMapping (path="/search") 
    public ModelAndView getSearch (HttpSession session) {
        ModelAndView mvc = new ModelAndView();
        if(session.getAttribute("email") == null) {
            mvc.setViewName("redirect:/");
            return mvc;
        }
        mvc.setViewName("search");
        mvc.setStatus(HttpStatus.OK);

        return mvc;
    }

    @GetMapping (path="search/searchResult")
    public ModelAndView getSearchQuery (@RequestParam String q, HttpSession session) {
        ModelAndView mvc = new ModelAndView();
        if(session.getAttribute("email") == null) {
            mvc.setViewName("redirect:/");
            return mvc;
        }

        System.out.printf(">>> query: ", q);

        Optional<List<Book>> optBookLists = searchSvcs.getBookSearch(q);
        List<Book> books = optBookLists.get();
        
        System.out.println("what is the list of books: "  + books.toString());
        
        if (optBookLists.isEmpty()) {
            mvc.setStatus(HttpStatus.BAD_REQUEST);
            mvc.addObject("message", "No books were found!");
            mvc.setViewName("searchResult");
            return mvc;
        }
        mvc.setViewName("searchResult");
        mvc.setStatus(HttpStatus.OK);
        mvc.addObject("message", q);
        mvc.addObject("books", books);

        return mvc;
    }

    @PostMapping (path="search/searchResult/added")
    public ModelAndView addingBookToLibrary(String book_id, HttpSession session) {
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

        String message = null;
        try {
            addBookSvcs.addBookAndReview(user_id, book_id); 
            message = "Added successfully";
        } catch (BookAndReviewExistedException err) {
            message = "Book record has already been recorded in the database and you have already gotten a review on the book";
        }
        

        mvc.setViewName("home");
        mvc.addObject("username", username);
        mvc.addObject("user_id", user_id);
        mvc.addObject("message", message);
        mvc.setStatus(HttpStatus.OK);

        return mvc;
    }

    @GetMapping(path="/post")
    public ModelAndView getPost (HttpSession session) {
        ModelAndView mvc = new ModelAndView();
        if(session.getAttribute("email") == null) {
            mvc.setViewName("redirect:/");
            return mvc;
        }
        mvc.setViewName("post");
        mvc.setStatus(HttpStatus.OK);

        return mvc;
    }

    //Still need to work on the post page. It is not updating the SQL table

    @PostMapping(path="/post/posted")
    public ModelAndView writeAPost (@RequestBody MultiValueMap<String, String> form, HttpSession session) {
        ModelAndView mvc = new ModelAndView();
        if(session.getAttribute("email") == null) {
            mvc.setViewName("redirect:/");
            return mvc;
        }

        System.out.println(form);
        //need to write the service for post
        postSvcs.addPost(form);

        mvc.setViewName("posted");
        mvc.setStatus(HttpStatus.OK);

        return mvc;
    }

    @GetMapping(path="/post/posted")
    public ModelAndView getPosted (HttpSession session) {
        ModelAndView mvc = new ModelAndView();
        if(session.getAttribute("email") == null) {
            mvc.setViewName("redirect:/");
            return mvc;
        }
        mvc.setViewName("posted");
        mvc.setStatus(HttpStatus.OK);

        return mvc;
    }
}
