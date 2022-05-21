// package sg.edu.nus.VTTP2022.miniproject.BookClub.controllers;

// import java.util.List;
// import java.util.Optional;

// import javax.servlet.http.HttpSession;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpStatus;
// import org.springframework.stereotype.Controller;
// import org.springframework.util.MultiValueMap;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestParam;
// import org.springframework.web.servlet.ModelAndView;

// import sg.edu.nus.VTTP2022.miniproject.BookClub.models.Book;
// import sg.edu.nus.VTTP2022.miniproject.BookClub.services.SearchService;

// @Controller
// @RequestMapping
// public class SearchController {

//     @Autowired
//     private SearchService searchSvcs;

//     // @GetMapping (path="/{viewPage}")
//     // public ModelAndView viewSearchPage (@PathVariable String viewPage, HttpSession session) {
        
//     //     String email = (String)session.getAttribute("email");

//     //     ModelAndView mvc = new ModelAndView();
//     //     mvc.setViewName("search");

//     //     return mvc;
//     // }

//     @GetMapping (path="/search")
//     public ModelAndView viewSearchPage (HttpSession session) {
        
//         String email = (String)session.getAttribute("email");

//         ModelAndView mvc = new ModelAndView();
//         mvc = new ModelAndView("redirect:/protected/search");
//         // mvc.setViewName("search");

//         return mvc;
//     }
    
//     @PostMapping (path="/search/q")
//     public ModelAndView getBookSearch (@PathVariable (name="query") String q, HttpSession session) {

//         String email = (String)session.getAttribute("email");

//         System.out.printf(">>> query: ", q);

//         Optional<List<Book>> optBookLists = searchSvcs.getBookSearch(q);

//         List<Book> books = optBookLists.get();
//         System.out.println("what is the list of books: "  + books.toString());

//         ModelAndView mvc = new ModelAndView();

//         if (optBookLists.isEmpty()) {
//             mvc.setStatus(HttpStatus.BAD_REQUEST);
//             mvc.addObject("message", "No books were found!");
//             mvc.setViewName("search");
//             return mvc;
//         }

//         mvc = new ModelAndView("redirect:/protected/search/q");
//         // mvc.setViewName("redirect:/search");
//         mvc.setStatus(HttpStatus.OK);
//         mvc.addObject("books", books);
        
//         return mvc;
//     }

//     // @PostMapping
//     // public ModelAndView addBookIntoLibrary (@RequestBody MultiValueMap<String, String> payload) {
//     //     ModelAndView mvc = new ModelAndView();
//     //     return mvc;
//     // }
// }
