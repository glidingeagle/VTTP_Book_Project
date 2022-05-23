package sg.edu.nus.VTTP2022.miniproject.BookClub.models;

public class Review {
    private Integer review_id;
    private Integer status;
    private Float rating;
    private String comment;

    public Integer getReview_id() {
        return review_id;
    }
    public void setReview_id(Integer review_id) {
        this.review_id = review_id;
    }
    public Integer getStatus() {
        return status;
    }
    public void setStatus(Integer status) {
        this.status = status;
    }
    public Float getRating() {
        return rating;
    }
    public void setRating(Float rating) {
        this.rating = rating;
    }
    public String getComment() {
        return comment;
    }
    public void setComment(String comment) {
        this.comment = comment;
    }
}
