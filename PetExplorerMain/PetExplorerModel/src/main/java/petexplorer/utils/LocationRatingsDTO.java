package petexplorer.utils;

import java.util.List;

public class LocationRatingsDTO {

    private Integer locationId;
    private String locationType;
    private Double averageRating;
    private Integer reviewCount;
    private List<RatingResponseDTO> ratings;

    public LocationRatingsDTO() {
    }

    public LocationRatingsDTO(Integer locationId, String locationType, Double averageRating, Integer reviewCount, List<RatingResponseDTO> ratings) {
        this.locationId = locationId;
        this.locationType = locationType;
        this.averageRating = averageRating;
        this.reviewCount = reviewCount;
        this.ratings = ratings;
    }

    public Integer getLocationId() {
        return locationId;
    }

    public void setLocationId(Integer locationId) {
        this.locationId = locationId;
    }

    public String getLocationType() {
        return locationType;
    }

    public void setLocationType(String locationType) {
        this.locationType = locationType;
    }

    public Double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(Double averageRating) {
        this.averageRating = averageRating;
    }

    public Integer getReviewCount() {
        return reviewCount;
    }

    public void setReviewCount(Integer reviewCount) {
        this.reviewCount = reviewCount;
    }

    public List<RatingResponseDTO> getRatings() {
        return ratings;
    }

    public void setRatings(List<RatingResponseDTO> ratings) {
        this.ratings = ratings;
    }
}


