package petexplorer;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import petexplorer.domain.Rating;
import petexplorer.domain.User;
import petexplorer.domain.enums.LocationType;
import petexplorer.ratingsrepos.RatingRepository;
import petexplorer.userrepos.UserRepository;
import petexplorer.utils.LocationRatingsDTO;
import petexplorer.utils.RatingRequestDTO;
import petexplorer.utils.RatingResponseDTO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class RatingController {

    private final RatingRepository ratingRepository;
    private final UserRepository userRepository;

    public RatingController(RatingRepository ratingRepository, UserRepository userRepository) {
        this.ratingRepository = ratingRepository;
        this.userRepository = userRepository;
    }

    @PostMapping("/ratings")
    public RatingResponseDTO addOrUpdateRating(@RequestBody RatingRequestDTO dto) {
        if (dto.getRatingValue() == null || dto.getRatingValue() < 1 || dto.getRatingValue() > 5) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ratingValue must be between 1 and 5");
        }

        if (dto.getUserId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "userId is required");
        }

        if (dto.getLocationId() == null || dto.getLocationType() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "locationId and locationType are required");
        }

        User user = userRepository.findOne(dto.getUserId());
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }

        LocationType locationType;
        try {
            locationType = LocationType.valueOf(dto.getLocationType().toUpperCase());
        } catch (IllegalArgumentException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid locationType");
        }

        Rating rating = ratingRepository.findByUserAndLocation(dto.getUserId(), locationType, dto.getLocationId());
        LocalDateTime now = LocalDateTime.now();

        if (rating == null) {
            rating = new Rating();
            rating.setUser(user);
            rating.setLocationType(locationType);
            rating.setLocationId(dto.getLocationId());
        }

        rating.setRatingValue(dto.getRatingValue());
        rating.setReviewText(dto.getReviewText());
        rating.setTimestamp(now);

        if (rating.getId() == null) {
            ratingRepository.save(rating);
        } else {
            ratingRepository.update(rating);
        }

        return new RatingResponseDTO(
                rating.getUser().getId(),
                rating.getUser().getNume(),
                rating.getRatingValue(),
                rating.getReviewText(),
                rating.getTimestamp()
        );
    }

    @GetMapping("/locations/{locationId}/ratings")
    public LocationRatingsDTO getRatingsForLocation(@PathVariable Integer locationId,
                                                    @RequestParam("type") String locationTypeString) {
        if (locationTypeString == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "type is required");
        }

        LocationType locationType;
        try {
            locationType = LocationType.valueOf(locationTypeString.toUpperCase());
        } catch (IllegalArgumentException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid location type");
        }

        List<Rating> ratings = ratingRepository.findByLocation(locationType, locationId);

        int count = ratings.size();
        double average = 0.0;
        if (count > 0) {
            int sum = ratings.stream()
                    .map(Rating::getRatingValue)
                    .filter(v -> v != null)
                    .mapToInt(Integer::intValue)
                    .sum();
            average = (double) sum / count;
        }

        List<RatingResponseDTO> ratingDTOs = ratings.stream()
                .map(r -> new RatingResponseDTO(
                        r.getUser() != null ? r.getUser().getId() : null,
                        r.getUser() != null ? r.getUser().getNume() : null,
                        r.getRatingValue(),
                        r.getReviewText(),
                        r.getTimestamp()
                ))
                .collect(Collectors.toList());

        return new LocationRatingsDTO(
                locationId,
                locationType.name(),
                count > 0 ? average : null,
                count,
                ratingDTOs
        );
    }
}


