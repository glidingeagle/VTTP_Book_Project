package sg.edu.nus.VTTP2022.miniproject.BookClub.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;

@Component
public class AuthenticationFilter implements Filter {
    
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterchain) throws IOException, ServletException {

        HttpServletRequest httpReq = (HttpServletRequest) request;
        HttpServletResponse httpResp = (HttpServletResponse) response;

        HttpSession session = httpReq.getSession();
        String email = (String)session.getAttribute("email");

        //Need to do validate email present in database. The condition below do not have validation of email present
        if(email == null || email.trim().length() <= 0) {
            httpResp.sendRedirect("/index.html");
            return;
        }

        filterchain.doFilter(request, response);
    }
}
