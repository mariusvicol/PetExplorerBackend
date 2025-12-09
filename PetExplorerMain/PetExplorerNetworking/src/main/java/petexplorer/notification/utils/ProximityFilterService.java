package petexplorer.notification.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import petexplorer.service.UserLocationService;
import petexplorer.domain.UserLocation;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProximityFilterService {
    private final UserLocationService userLocationService;
    private final DistanceCalculator distanceCalculator;

    @Value("${notification.radius.default}")
    private double notificationRadius;

    public ProximityFilterService(UserLocationService userLocationService, DistanceCalculator distanceCalculator) {
        this.userLocationService = userLocationService;
        this.distanceCalculator = distanceCalculator;
    }

    public Iterable<Integer> getUsersInProximity(double eventLatitude, double eventLongitude) {
        List<Integer> usersInProximity = new ArrayList<>();
        for (UserLocation userLocation: userLocationService.getUserLocations()) {
            if (distanceCalculator.getDistance(userLocation.getLatitude(), userLocation.getLongitude(), eventLatitude, eventLongitude) <= notificationRadius) {
                usersInProximity.add(userLocation.getUserId());
            }
        }
        return usersInProximity;
    }
}
