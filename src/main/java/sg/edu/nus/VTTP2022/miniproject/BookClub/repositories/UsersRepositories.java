package sg.edu.nus.VTTP2022.miniproject.BookClub.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import sg.edu.nus.VTTP2022.miniproject.BookClub.models.User;

import static sg.edu.nus.VTTP2022.miniproject.BookClub.repositories.Queries.*;
import static sg.edu.nus.VTTP2022.miniproject.BookClub.models.ConversionUtils.*;

import java.util.Optional;

@Repository
public class UsersRepositories {
    
    @Autowired
    private JdbcTemplate template;

    //for logging in
    public int countUserByEmailAndPassword(String email, String password) {
        SqlRowSet rs = template.queryForRowSet(SQL_COUNT_USER_BY_EMAIL, email, password);
        
        if (!rs.next())
            return 0;

        return rs.getInt("email_count");
    }

    //for signing up
    public boolean insertNewUserInformation (User user) {

        int count = template.update(SQL_INSERT_NEW_USER, user.getUser_id(), user.getUsername(), user.getPassword(), user.getEmail());

        return 1 == count;
    }

    public Optional<User> findUserByEmail (String email) {
        SqlRowSet rs = template.queryForRowSet(SQL_FIND_USER_BY_EMAIL, email);
        
        if(!rs.next())
            return Optional.empty();

        return Optional.of(convertUser(rs));
    }

}
