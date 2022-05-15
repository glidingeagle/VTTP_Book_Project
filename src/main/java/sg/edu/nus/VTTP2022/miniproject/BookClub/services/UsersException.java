package sg.edu.nus.VTTP2022.miniproject.BookClub.services;

public class UsersException extends Exception {
    
    private String reason;

    public UsersException (String reason) {
        this.reason = reason;
    }

    public String getReason() {
        return reason;
    }

}
