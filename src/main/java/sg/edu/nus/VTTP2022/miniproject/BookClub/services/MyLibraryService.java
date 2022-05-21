package sg.edu.nus.VTTP2022.miniproject.BookClub.services;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.nus.VTTP2022.miniproject.BookClub.models.Book;
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

    public List<Book> getWantToReadBook (String user_id, int status) {

        Optional<List<Book>> wantToReadOpt = myLibraryRepo.findBookReviewStatus(user_id, status);
        List<Book> wantToRead = new LinkedList<>();

        if (wantToReadOpt.isEmpty())
            return List.of();

        if (wantToReadOpt.isPresent()) {
            wantToRead = wantToReadOpt.get();
            for(int i = 0; i<wantToRead.size(); i ++) {
                Book wantToReadBook = wantToRead.get(i);
                wantToReadBook.getImageLink();
                wantToReadBook.getTitle();
            }
        }
        return wantToRead;
        
    }

    public List<Book> getCurrentlyReadingBook (String user_id, int status) {

        Optional<List<Book>> currentlyReadingOpt = myLibraryRepo.findBookReviewStatus(user_id, status);
        List<Book> currentlyReading = new LinkedList<>();

        if (currentlyReadingOpt.isEmpty())
            return List.of();

        if (currentlyReadingOpt.isPresent()) {
            currentlyReading = currentlyReadingOpt.get();
            for(int i = 0; i<currentlyReading.size(); i ++) {
                Book currentlyReadingBook = currentlyReading.get(i);
                currentlyReadingBook.getImageLink();
                currentlyReadingBook.getTitle();
            }
        }
        return currentlyReading;
    }

    public List<Book> getReadBook (String user_id, int status) {

        Optional<List<Book>> readOpt = myLibraryRepo.findBookReviewStatus(user_id, status);
        List<Book> read = new LinkedList<>();

        if (readOpt.isEmpty())
            return List.of();

        if (readOpt.isPresent()) {
            read = readOpt.get();
            for(int i = 0; i<read.size(); i ++) {
                Book readBook = read.get(i);
                readBook.getImageLink();
                readBook.getTitle();
            }
        }
        return read;
    }

    //this is for viewing rating and comments when click into the imageLink in Homepage

    // public List<Review> getUserLibrary(String user_id) {

    //     Optional<List<Review>> optReviewLists = myLibraryRepo.findBooksAndReviewsByUserId(user_id);
        
    //     List<Review> reviewLists = new LinkedList<>();

    //     if(optReviewLists.isEmpty()) {
    //         reviewLists = null;
    //     }
        
    //     if(optReviewLists.isPresent()) {
    //         reviewLists = optReviewLists.get();
    //     } 
    //     return reviewLists;
    // }
}
