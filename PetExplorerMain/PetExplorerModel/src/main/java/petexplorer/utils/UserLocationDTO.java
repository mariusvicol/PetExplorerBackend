package petexplorer.utils;

public class UserLocationDTO {
    private int userId;
    private double latitude;
    private double longitude;

    public UserLocationDTO(int userId, double latitude, double longitude) {
        this.userId = userId;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String toString() {
        return "UserLocationDTO{" +
                "userId=" + userId +
                " ,latitude=" + latitude +
                " ,longitude=" + longitude +
                "}";
    }
}
