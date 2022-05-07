package sg.edu.nus.VTTP2022.miniproject.BookClub.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import sg.edu.nus.VTTP2022.miniproject.BookClub.models.Book;
import sg.edu.nus.VTTP2022.miniproject.BookClub.services.SearchService;

@Controller
@RequestMapping (path="/")
public class SearchController {

    @Autowired
    private SearchService searchSvcs;
    
    @GetMapping (path="/search")
    public ModelAndView getBookSearch (@RequestParam (name="query") String q) {

        Optional<List<Book>> optBookLists = searchSvcs.getBookSearch(q);

        List<Book> books = optBookLists.get();
        System.out.println("what is the list of books: "  + books.toString());

        ModelAndView mvc = new ModelAndView();

        if (optBookLists.isEmpty()) {
            mvc.setStatus(HttpStatus.BAD_REQUEST);
            mvc.setViewName("error");
            return mvc;
        }

        mvc.setViewName("search");
        mvc.setStatus(HttpStatus.OK);
        mvc.addObject("books", books);
        
        return mvc;
    }
}
