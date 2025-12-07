package petexplorer.utils;

public class UserLocationVO {
    private final int userId;
    private final double latitude;
    private final double longitude;

    public UserLocationVO(int userId, double latitude, double longitude) {
        this.userId = userId;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public int getUserId() {
        return userId;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
}
