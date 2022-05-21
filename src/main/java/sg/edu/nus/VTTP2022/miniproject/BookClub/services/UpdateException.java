package sg.edu.nus.VTTP2022.miniproject.BookClub.services;

public class UpdateException extends Exception{

    private String reason;

    public UpdateException (String reason) {
        this.reason = reason;
    }

    public String getReason() {
        return reason;
    }
}
