package sg.edu.nus.VTTP2022.miniproject.BookClub.models;

public class Review {
    private int review_id;
    private int review_status;
    private float review_rating;
    private String review_quotes;
    private String review_comments;
    private String user_id;
    private int book_id;

    public int getReview_id() {
        return review_id;
    }
    public void setReview_id(int review_id) {
        this.review_id = review_id;
    }
    public int getReview_status() {
        return review_status;
    }
    public void setReview_status(int review_status) {
        this.review_status = review_status;
    }
    public float getReview_rating() {
        return review_rating;
    }
    public void setReview_rating(float review_rating) {
        this.review_rating = review_rating;
    }
    public String getReview_quotes() {
        return review_quotes;
    }
    public void setReview_quotes(String review_quotes) {
        this.review_quotes = review_quotes;
    }
    public String getReview_comments() {
        return review_comments;
    }
    public void setReview_comments(String review_comments) {
        this.review_comments = review_comments;
    }
    public String getUser_id() {
        return user_id;
    }
    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
    public int getBook_id() {
        return book_id;
    }
    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

}
