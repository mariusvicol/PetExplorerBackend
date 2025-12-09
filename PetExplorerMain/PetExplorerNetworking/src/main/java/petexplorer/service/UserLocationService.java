package petexplorer.service;

import org.springframework.stereotype.Service;
import petexplorer.domain.UserLocation;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserLocationService {
    private final Map<Integer, UserLocation> userLocations;

    public UserLocationService() {
        userLocations = new HashMap<Integer, UserLocation>();
    }

    public void save(UserLocation userLocation) {
        userLocations.put(userLocation.getUserId(), userLocation);
    }

    public Iterable<UserLocation> getUserLocations() {
        return userLocations.values();
    }
}
