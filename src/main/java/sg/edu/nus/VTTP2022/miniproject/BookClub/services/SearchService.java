package sg.edu.nus.VTTP2022.miniproject.BookClub.services;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
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

    // private static List<Book> books = new LinkedList<>();

    public Optional<List<Book>> getBookSearch(String q) {

        String maxResults = "8";

        //building the urlString
        String googleBookUrl = UriComponentsBuilder.fromUriString(URL)
                .queryParam("q", q.trim().replaceAll(" ", "+"))
                .queryParam("maxResults", maxResults)
                .queryParam("key", apiKey)
                .toUriString();
        
        //Check to see the url is correct
        System.out.println(">>>> Google Book Search Url: " + googleBookUrl);

        RequestEntity<Void> request = RequestEntity
                .get(googleBookUrl)
                .build();

        RestTemplate template = new RestTemplate();
        ResponseEntity<String> response = template.exchange(request, String.class);

        // try {
        //     response = template.exchange(request, String.class);
        //     List<Book> books = Book.create(response.getBody());
        //     return Optional.of(books);
        // } catch (Exception ex) {
        //     System.err.printf(">>> Error: %s\n", ex.getMessage());
        //     ex.printStackTrace();
        // }
        // return Optional.empty();

        try(InputStream is = new ByteArrayInputStream(response.getBody().getBytes())) {
            JsonReader reader = Json.createReader(is);
            JsonArray bookArray = reader.readObject().getJsonArray("items");

            System.out.println(">>>> books json: " + bookArray.toString());

            List<Book> books = new LinkedList<>();
            for (int i = 0; i < bookArray.size(); i++) {
                JsonObject objBooks = bookArray.getJsonObject(i);
                Book book = new Book();
                book.setBook_id(objBooks.getString("id"));
                book.setTitle(objBooks.getJsonObject("volumeInfo").getString("title"));
                book.setInfoLink(objBooks.getJsonObject("volumeInfo").getString("infoLink"));
                book.setImageLink(objBooks.getJsonObject("volumeInfo").getJsonObject("imageLinks").getString("smallThumbnail"));

                JsonArray authorLists = objBooks.getJsonObject("volumeInfo").getJsonArray("authors");
                System.out.println(">>> authors: " + authorLists);
                List<String> authors = new LinkedList<>();
                for (int j = 0; j<authorLists.size(); j++) {
                    
                    if (authorLists.isEmpty())
                        break;

                    String author = authorLists.getString(j);
                    authors.add(author);
                }
                book.setAuthors(authors);
                System.out.println(">>>> Book authors: " + authors);

                books.add(book);
            }
            System.out.println(">>>>>> books size: " + books.size());
            return Optional.of(books);
        } catch (Exception ex) {
            ex.printStackTrace();
            return Optional.empty();
        }
    }

    public Book getBookSearchById (String query) {

        String googleBookUrl = UriComponentsBuilder.fromUriString(URL)
                .queryParam("q", query)
                .queryParam("key", apiKey)
                .toUriString();

        RequestEntity<Void> request = RequestEntity
                .get(googleBookUrl)
                .build();

        RestTemplate template = new RestTemplate();
        ResponseEntity<String> response = template.exchange(request, String.class);

        Book book = new Book();
        try(InputStream is = new ByteArrayInputStream(response.getBody().getBytes())) {
            JsonReader reader = Json.createReader(is);
            JsonArray bookArray = reader.readObject().getJsonArray("items");

            int i = 0;
            JsonObject objBooks = bookArray.getJsonObject(i);
            book.setBook_id(objBooks.getString("id"));
            book.setTitle(objBooks.getJsonObject("volumeInfo").getString("title"));
            book.setInfoLink(objBooks.getJsonObject("volumeInfo").getString("infoLink"));
            book.setImageLink(objBooks.getJsonObject("volumeInfo").getJsonObject("imageLinks").getString("smallThumbnail"));


            JsonArray authorLists = objBooks.getJsonObject("volumeInfo").getJsonArray("authors");
            System.out.println(">>> authors: " + authorLists);
            List<String> authors = new LinkedList<>();
            for (int j = 0; j<authorLists.size(); j++) {
                
                if (authorLists.isEmpty())
                    break;

                String author = authorLists.getString(j);
                authors.add(author);
            }
            book.setAuthors(authors);
            System.out.println(">>>> Book authors: " + authors);
            
            return book;
        } catch (Exception ex) {
            ex.printStackTrace();
            return book;
        }
    }
}
