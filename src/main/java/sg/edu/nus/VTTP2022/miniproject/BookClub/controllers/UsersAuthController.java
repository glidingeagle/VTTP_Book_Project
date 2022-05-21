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

import sg.edu.nus.VTTP2022.miniproject.BookClub.models.User;
import sg.edu.nus.VTTP2022.miniproject.BookClub.services.UsersAuthService;
import sg.edu.nus.VTTP2022.miniproject.BookClub.services.UsersException;

import static sg.edu.nus.VTTP2022.miniproject.BookClub.models.ConversionUtils.*;

@Controller
@RequestMapping
public class UsersAuthController {
    
    @Autowired
    private UsersAuthService userAuthSvc;

    @GetMapping (path="/")
    public ModelAndView viewLogin () {
        ModelAndView mvc = new ModelAndView();
        mvc.setViewName("index");

        return mvc;
    }

    @GetMapping(path="/signup")
    public ModelAndView viewSignup () {
        ModelAndView mvc = new ModelAndView();
        mvc.setViewName("signup");

        return mvc;
    }

    @PostMapping (path="/index")
    public ModelAndView getNewUser (@RequestBody MultiValueMap<String, String> payload) {

        User user = convert(payload);

        ModelAndView mvc = new ModelAndView();

        try {
            userAuthSvc.enterNewUser(user);
            mvc.addObject("message", "%s has been successfully created!".formatted(user.getUsername()));
        } catch (UsersException ex) {
            mvc.addObject("message", "%s".formatted(ex.getReason()));
            mvc.setStatus(HttpStatus.BAD_REQUEST);
            ex.printStackTrace();
        }

        mvc.setViewName("index");

        return mvc;
    }

    @GetMapping (path="/logout")
    public String getLogout (HttpSession session) {
        session.invalidate();
        //return "index";
        return "redirect:/";
    }

    @PostMapping (path="/home")
    public ModelAndView postIndex (@RequestBody MultiValueMap<String, String> payload, HttpSession session) {
        String email = payload.getFirst("email");
        String password = payload.getFirst("password");

        System.out.printf(">>> email: %s\n", email);
        System.out.printf(">>> password: %s\n", password);

        // ModelAndView mvc;
        ModelAndView mvc = new ModelAndView();

        if(!userAuthSvc.authentication(email, password)) {
            mvc.addObject("message", "Log in failed! Check with administrator.");
            mvc.setStatus(HttpStatus.FORBIDDEN);
            // mvc = new ModelAndView("error");
            mvc.setViewName("index");
        } else {
            session.setAttribute("email", email);
            mvc = new ModelAndView("redirect:/protected/home");
            // mvc = new ModelAndView("redirect:/protected/search");
        }
        return mvc;
    }
}
