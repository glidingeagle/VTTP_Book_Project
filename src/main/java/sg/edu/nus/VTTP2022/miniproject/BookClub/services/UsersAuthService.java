package sg.edu.nus.VTTP2022.miniproject.BookClub.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.nus.VTTP2022.miniproject.BookClub.models.User;
import sg.edu.nus.VTTP2022.miniproject.BookClub.repositories.UsersRepositories;

@Service
public class UsersAuthService {
    
    @Autowired
    private UsersRepositories usersRepo;

    public boolean authentication (String email, String password) {
        return 1 == usersRepo.countUserByEmailAndPassword(email, password);
    }

    public void enterNewUser (User user) throws UsersException {

        Optional<User> optUser = usersRepo.findUserByEmail(user.getEmail());
        if (optUser.isPresent()) 
            throw new UsersException("Sorry! User has already been registered. Please contact the administrator.");

        if(!usersRepo.insertNewUserInformation(user))
        throw new UsersException("Sorry! System unable to add user. Please contact the administrator.");
    }
}
