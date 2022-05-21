package sg.edu.nus.VTTP2022.miniproject.BookClub.models;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

public class Book {
    private String book_id;
    private String title;
    private String infoLink;
    private String imageLink;
    private List<String> authors;
    
    public String getBook_id() {
        return book_id;
    }
    public void setBook_id(String book_id) {
        this.book_id = book_id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getInfoLink() {
        return infoLink;
    }
    public void setInfoLink(String infoLink) {
        this.infoLink = infoLink;
    }
    public String getImageLink() {
        return imageLink;
    }
    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }
    public List<String> getAuthors() {
        return authors;
    }
    public void setAuthors(List<String> authors) {
        this.authors = authors;
    }
    
    @Override
    public String toString() {
        return "Books [title = " + title + ", infoLink = " + infoLink + ", imageLink = " + imageLink + "]";
    }
    
    // public static List<Book> create(String json) throws IOException {
        
    //     try (InputStream is = new ByteArrayInputStream(json.getBytes())) {
    //         List<Book> books = new LinkedList<>();
    //         JsonReader reader = Json.createReader(is);
    //         JsonObject bookDetails = reader.readObject();
    //         JsonArray items = bookDetails.getJsonArray("items");
    //         for (int i = 0; i < items.size(); i++) {
    //             Book book = new Book();
    //             JsonObject bookDetail = items.getJsonObject(i);
    //             String title = bookDetail.getJsonObject("volumeInfo").getString("title");
    //             book.setTitle(title);
    //             System.out.println(">>>> Book Title: " + title);

    //             String infoLink = bookDetail.getJsonObject("volumeInfo").getString("infoLink");
    //             book.setInfoLink(infoLink);
    //             System.out.println(">>>> Book InfoLink: " + infoLink);

    //             String imageLink = bookDetail.getJsonObject("volumeInfo").getJsonObject("imageLinks").getString("smallThumbnail");
    //             book.setImageLink(imageLink);
    //             System.out.println(">>>> Book ImageLink: " + imageLink);

    //             JsonArray authorLists = bookDetail.getJsonObject("volumeInfo").getJsonArray("authors");
    //             List<String> authors = new LinkedList<String>();
    //             for (int j = 0; j < authorLists.size(); j++) {
    //                 String author = authorLists.getString(j);
    //                 authors.add(author);
    //             }
    //             book.setAuthors(authors);
    //             System.out.println(">>>> Book authors: " + authors);
    //             books.add(book);
    //         }
    //         System.out.println(books);
    //         return books;
    //     }
        
    // }
}
