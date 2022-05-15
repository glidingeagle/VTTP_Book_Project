package sg.edu.nus.VTTP2022.miniproject.BookClub.services;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.nus.VTTP2022.miniproject.BookClub.models.Review;
import sg.edu.nus.VTTP2022.miniproject.BookClub.models.User;
import sg.edu.nus.VTTP2022.miniproject.BookClub.repositories.MyLibraryRepositories;
import sg.edu.nus.VTTP2022.miniproject.BookClub.repositories.UsersRepositories;

@Service
public class MyLibraryService {

    @Autowired
    private UsersRepositories usersRepo;
    
    @Autowired 
    private MyLibraryRepositories myLibraryRepo;

    public User getUser(String email) {
        Optional<User> optUser = usersRepo.findUserByEmail(email);
        
        User user = new User();
        if(optUser.isPresent()) {
            user = optUser.get();
            user.getUser_id();
            user.getUsername();
        }
        return user;
    }

    public List<Review> getUserLibrary(String user_id) {

        Optional<List<Review>> optReviewLists = myLibraryRepo.findBooksAndReviewsByUserId(user_id);
        
        List<Review> reviewLists = new LinkedList<>();

        if(optReviewLists.isEmpty()) {
            reviewLists = null;
        }
        
        if(optReviewLists.isPresent()) {
            reviewLists = optReviewLists.get();
        } 
        return reviewLists;
    }
}
