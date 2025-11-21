package petexplorer.domain;

import jakarta.persistence.*;
import petexplorer.domain.enums.LocationType;

import java.io.Serializable;
import java.time.LocalDateTime;

@jakarta.persistence.Entity
@Table(name = "ratings")
public class Rating extends Entity<Integer> implements Serializable {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(name = "location_type", nullable = false)
    private LocationType locationType;

    @Column(name = "location_id", nullable = false)
    private Integer locationId;

    @Column(name = "rating_value", nullable = false)
    private Integer ratingValue;

    @Column(name = "review_text")
    private String reviewText;

    @Column(name = "timestamp", nullable = false)
    private LocalDateTime timestamp;

    public Rating() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocationType getLocationType() {
        return locationType;
    }

    public void setLocationType(LocationType locationType) {
        this.locationType = locationType;
    }

    public Integer getLocationId() {
        return locationId;
    }

    public void setLocationId(Integer locationId) {
        this.locationId = locationId;
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

    @Override
    public String toString() {
        return "Rating{" +
                "id=" + id +
                ", user=" + (user != null ? user.getId() : null) +
                ", locationType=" + locationType +
                ", locationId=" + locationId +
                ", ratingValue=" + ratingValue +
                ", reviewText='" + reviewText + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}


