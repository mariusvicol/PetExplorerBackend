package petexplorer.notification.utils;

public interface DistanceCalculator {
    double EARTH_RADIUS = 6371;

    double getDistance(double lat1, double lon1, double lat2, double lon2);
}
