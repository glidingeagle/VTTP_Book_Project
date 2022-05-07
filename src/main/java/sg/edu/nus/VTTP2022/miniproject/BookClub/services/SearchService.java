package sg.edu.nus.VTTP2022.miniproject.BookClub.services;

import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import sg.edu.nus.VTTP2022.miniproject.BookClub.models.Book;

@Service
public class SearchService {
    
    private static final String URL = "https://www.googleapis.com/books/v1/volumes";

    //set GOOGLE_BOOK_APIKEY=xxx
    @Value ("${google.book.apiKey}")
    private String apiKey; 

    private boolean hasKey;

    @PostConstruct
    private void init() {
        hasKey = null != apiKey;
        System.out.println(">>>> API key set: " + hasKey);
    }

    public Optional<List<Book>> getBookSearch(String q) {

        //building the urlString
        String googleBookUrl = UriComponentsBuilder.fromUriString(URL)
                .queryParam("q", q.replaceAll(" ", "+"))
                .queryParam("key", apiKey)
                .toUriString();
        
        //Check to see the url is correct
        System.out.println(">>>> Google Book Search Url: " + googleBookUrl);

        RequestEntity<Void> request = RequestEntity
                .get(googleBookUrl)
                .build();

        RestTemplate template = new RestTemplate();
        ResponseEntity<String> response = null;

        try {
            response = template.exchange(request, String.class);
            List<Book> books = Book.create(response.getBody());
            return Optional.of(books);
        } catch (Exception ex) {
            System.err.printf(">>> Error: %s\n", ex.getMessage());
            ex.printStackTrace();
        }
        return Optional.empty();
    }
}
