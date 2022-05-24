package sg.edu.nus.VTTP2022.miniproject.BookClub.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import sg.edu.nus.VTTP2022.miniproject.BookClub.services.PostService;

@Controller
@RequestMapping (path="/protected")
public class PostController {
    
    @Autowired
    private PostService postSvcs;

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

    @PostMapping(path="/post/posted")
    public ModelAndView writeAPost (@RequestBody MultiValueMap<String, String> form, HttpSession session){
        ModelAndView mvc = new ModelAndView();
        if(session.getAttribute("email") == null) {
            mvc.setViewName("redirect:/");
            return mvc;
        }

        System.out.println(form);

        //Important: I need to be able to check whether the user enter the correct user

        // String user_id = form.getFirst("user_id");

        // String email = (String)session.getAttribute("email");
        // User user = myLibSvcs.getUser(email);
        // String user_id_from_login = user.getUser_id();
        // System.out.println(user_id_from_login);

        // String message = null;
        // if (user_id == user_id_from_login) {
        //     try {
        //         postSvcs.addPost(form); 
        //         message = "Posted successfully!";
        //     } catch (Exception excep) {
        //         message = "An error has occurred when posting!";
        //     }
        // } else if (user_id != user_id_from_login) {
        //     message = "Action Forbidden!";
        // }

        String message = null;
        try {
            postSvcs.addPost(form); 
            message = "Posted successfully!";
        } catch (Exception excep) {
            message = "An error has occurred when posting!";
        }
        
        mvc.setViewName("post");
        mvc.addObject("message", message);
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
        mvc.setViewName("post");
        mvc.setStatus(HttpStatus.OK);

        return mvc;
    }
}
