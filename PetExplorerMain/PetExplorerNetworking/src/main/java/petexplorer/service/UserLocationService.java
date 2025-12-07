package petexplorer.service;

import org.springframework.stereotype.Service;
import petexplorer.utils.UserLocationVO;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserLocationService {
    private final Map<Integer, UserLocationVO> userLocations;

    public UserLocationService() {
        userLocations = new HashMap<Integer, UserLocationVO>();
    }

    public void save(UserLocationVO userLocation) {
        userLocations.put(userLocation.getUserId(), userLocation);
    }

    public Iterable<UserLocationVO> getUserLocations() {
        return userLocations.values();
    }
}
