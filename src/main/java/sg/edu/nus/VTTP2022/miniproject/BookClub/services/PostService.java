package sg.edu.nus.VTTP2022.miniproject.BookClub.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.MultiValueMap;

import sg.edu.nus.VTTP2022.miniproject.BookClub.models.Book;
import sg.edu.nus.VTTP2022.miniproject.BookClub.repositories.AddBookRepositories;
import sg.edu.nus.VTTP2022.miniproject.BookClub.repositories.PostRepositories;

@Service
public class PostService {
    
    @Autowired
    private PostRepositories postRepo;

    @Autowired
    private SearchService searchSvcs;

    @Autowired
    private AddBookRepositories addBookRepo;

    @Transactional (rollbackFor = Exception.class)
    public void addPost (MultiValueMap<String, String> form) {

        String user_id = form.getFirst("user_id");
        String book_id = form.getFirst("book_id");
        int status = Integer.parseInt(form.getFirst("status"));
        float rating = Float.parseFloat(form.getFirst("rating"));
        String comment = form.getFirst("comment");

        System.out.println(user_id);
        System.out.println(book_id);
        System.out.println(status);
        System.out.println(rating);
        System.out.println(comment);

        //Can I write a try catch block along with transaction?
        //Can I try catch block within the if block or can I wrap it at the end of the if's blocks?

        if (addBookRepo.countBookByID (book_id) == 0 && addBookRepo.countReviewByUserAndBook(user_id, book_id) == 0) {
            Book book = searchSvcs.getBookSearchById(book_id);
            addBookRepo.insertBookRecord(book);
            postRepo.insertPostToReview(status, rating, comment, user_id, book_id);
        } else if (addBookRepo.countBookByID (book_id) == 0 && addBookRepo.countReviewByUserAndBook(user_id, book_id) != 0) {
            Book book = searchSvcs.getBookSearchById(book_id);
            addBookRepo.insertBookRecord(book);
            postRepo.updatePostToReview(status, rating, comment, user_id, book_id);
        } else if (addBookRepo.countBookByID (book_id) != 0 && addBookRepo.countReviewByUserAndBook(user_id, book_id) == 0) {
            postRepo.insertPostToReview(status, rating, comment, user_id, book_id);
        } else if (addBookRepo.countBookByID (book_id) != 0 && addBookRepo.countReviewByUserAndBook(user_id, book_id) != 0) {
            postRepo.updatePostToReview(status, rating, comment, user_id, book_id);
        }
    }
}
