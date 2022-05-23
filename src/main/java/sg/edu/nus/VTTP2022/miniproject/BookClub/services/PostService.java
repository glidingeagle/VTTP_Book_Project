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
    public void addPost (MultiValueMap<String, String> form) throws Exception{

        String user_id = form.getFirst("user_id");
        String book_id = form.getFirst("book_id");
        Integer status = Integer.parseInt(form.getFirst("status"));
        Float rating = Float.valueOf(form.getFirst("rating"));
        String comment = form.getFirst("comment");

        System.out.println(user_id);
        System.out.println(book_id);
        System.out.println(status);
        System.out.println(rating);
        System.out.println(comment);

        if (addBookRepo.countBookByID (book_id) == 0 && addBookRepo.countReviewByUserAndBook(user_id, book_id) == 0) {
            try{
                Book book = searchSvcs.getBookSearchById(book_id);
                addBookRepo.insertBookRecord(book);
                postRepo.insertPostToReview(status, rating, comment, user_id, book_id);
            } catch (Exception excep) {
                excep.printStackTrace();
                throw excep;
            } 
        } /*else if (addBookRepo.countBookByID (book_id) == 0 && addBookRepo.countReviewByUserAndBook(user_id, book_id) != 0) {
            try {
                Book book = searchSvcs.getBookSearchById(book_id);
                addBookRepo.insertBookRecord(book);
                postRepo.updatePostToReview(status, rating, comment, user_id, book_id);
            } catch (Exception excep) {
                excep.printStackTrace();
                throw excep;
            }  
        }*/ else if (addBookRepo.countBookByID (book_id) != 0 && addBookRepo.countReviewByUserAndBook(user_id, book_id) == 0) {

            try {
                postRepo.insertPostToReview(status, rating, comment, user_id, book_id);
            } catch (Exception excep) {
                excep.printStackTrace();
                throw excep;
            }  
            
        } else if (addBookRepo.countBookByID (book_id) != 0 && addBookRepo.countReviewByUserAndBook(user_id, book_id) != 0) {
            try {
                postRepo.updatePostToReview(status, rating, comment, user_id, book_id);
            } catch (Exception excep) {
                excep.printStackTrace();
                throw excep;
            }  
            
        } 
        
    }
}
