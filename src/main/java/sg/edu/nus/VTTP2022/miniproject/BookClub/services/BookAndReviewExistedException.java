package sg.edu.nus.VTTP2022.miniproject.BookClub.services;

public class BookAndReviewExistedException extends Exception{

    private String reason;

    public BookAndReviewExistedException (String reason) {
        this.reason = reason;
    }

    public String getReason() {
        return reason;
    }
}
