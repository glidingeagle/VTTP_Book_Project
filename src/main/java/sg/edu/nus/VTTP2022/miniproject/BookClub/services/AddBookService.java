package sg.edu.nus.VTTP2022.miniproject.BookClub.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sg.edu.nus.VTTP2022.miniproject.BookClub.models.Book;
import sg.edu.nus.VTTP2022.miniproject.BookClub.repositories.AddBookRepositories;

@Service
public class AddBookService {

    @Autowired
    private AddBookRepositories addBookRepo;

    @Autowired
    private SearchService searchSvcs;
    
    @Transactional (rollbackFor = Exception.class)
    public void addBookAndReview (String user_id, String book_id) throws Exception {

        if (addBookRepo.countBookByID (book_id) == 0 && addBookRepo.countReviewByUserAndBook(user_id, book_id) == 0) {
            try {
                Book book = searchSvcs.getBookSearchById(book_id);
                addBookRepo.insertBookRecord(book);
                addBookRepo.insertReviewRecord(user_id, book_id);
            } catch (Exception excep) {
                excep.printStackTrace();
                throw excep;
            }  
        } else if (addBookRepo.countReviewByUserAndBook(user_id, book_id) == 0 && addBookRepo.countBookByID (book_id) != 0) {
            try {
                addBookRepo.insertReviewRecord(user_id, book_id);
            }catch (Exception excep) {
                excep.printStackTrace();
                throw excep;
            }
        }   
    }
}
    
