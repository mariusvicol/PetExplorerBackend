package petexplorer.utils;

import java.time.LocalDateTime;

public class RatingResponseDTO {

    private Integer userId;
    private String userName;
    private Integer ratingValue;
    private String reviewText;
    private LocalDateTime timestamp;

    public RatingResponseDTO() {
    }

    public RatingResponseDTO(Integer userId, String userName, Integer ratingValue, String reviewText, LocalDateTime timestamp) {
        this.userId = userId;
        this.userName = userName;
        this.ratingValue = ratingValue;
        this.reviewText = reviewText;
        this.timestamp = timestamp;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getRatingValue() {
        return ratingValue;
    }

    public void setRatingValue(Integer ratingValue) {
        this.ratingValue = ratingValue;
    }

    public String getReviewText() {
        return reviewText;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}


