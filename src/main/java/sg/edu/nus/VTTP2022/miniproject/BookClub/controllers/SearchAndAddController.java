package sg.edu.nus.VTTP2022.miniproject.BookClub.controllers;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import sg.edu.nus.VTTP2022.miniproject.BookClub.models.Book;
import sg.edu.nus.VTTP2022.miniproject.BookClub.models.User;
import sg.edu.nus.VTTP2022.miniproject.BookClub.services.AddBookService;
import sg.edu.nus.VTTP2022.miniproject.BookClub.services.MyLibraryService;
import sg.edu.nus.VTTP2022.miniproject.BookClub.services.SearchService;

@Controller
@RequestMapping (path="/protected")
public class SearchAndAddController {

    @Autowired
    private MyLibraryService myLibSvcs;

    @Autowired
    private SearchService searchSvcs;

    @Autowired
    private AddBookService addBookSvcs;

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
            message = "Added successfully!";
        } catch (Exception excep) {
            message = "Book record has already been recorded in the database and you have already gotten a review on the book!";
        }
        

        mvc.setViewName("home");
        mvc.addObject("username", username);
        mvc.addObject("user_id", user_id);
        mvc.addObject("message", message);
        mvc.setStatus(HttpStatus.OK);

        return mvc;
    }

}
