package petexplorer.notification.utils;

import org.springframework.stereotype.Component;

@Component
public class DistanceCalculatorHaversine implements DistanceCalculator {
    @Override
    public double getDistance(double lat1, double lon1, double lat2, double lon2) {
        // Convert grades to radians
        double lat1Rad = Math.toRadians(lat1);
        double lon1Rad = Math.toRadians(lon1);
        double lat2Rad = Math.toRadians(lat2);
        double lon2Rad = Math.toRadians(lon2);

        double dLon = lon2Rad - lon1Rad;
        double dLat = lat2Rad - lat1Rad;

        // Haversine formula
        double a = Math.pow(Math.sin(dLat / 2), 2) +
                Math.cos(lat1Rad) * Math.cos(lat2Rad) * Math.pow(Math.sin(dLon / 2), 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return EARTH_RADIUS * c;
    }
}
