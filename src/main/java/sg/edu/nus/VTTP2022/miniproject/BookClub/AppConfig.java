package sg.edu.nus.VTTP2022.miniproject.BookClub;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import sg.edu.nus.VTTP2022.miniproject.BookClub.filters.AuthenticationFilter;

@Configuration
public class AppConfig {

    @Bean
    public FilterRegistrationBean<AuthenticationFilter> registerFilters() {
        AuthenticationFilter authFilter = new AuthenticationFilter();

        FilterRegistrationBean<AuthenticationFilter>regFilter = new FilterRegistrationBean<>();
        regFilter.setFilter(authFilter);
        regFilter.addUrlPatterns("/protected/*");

        return regFilter;
    }
    
}
