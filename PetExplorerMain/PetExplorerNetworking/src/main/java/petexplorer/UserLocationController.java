package petexplorer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import petexplorer.service.UserLocationService;
import petexplorer.utils.UserLocationDTO;
import petexplorer.utils.UserLocationVO;

@RestController
@RequestMapping("api/user_locations")
public class UserLocationController {
    @Autowired
    private final UserLocationService userLocationService;

    public UserLocationController(UserLocationService userLocationService) {
        this.userLocationService = userLocationService;
    }

    @PostMapping
    public ResponseEntity<UserLocationDTO> add(@RequestBody UserLocationDTO userLocationDTO) {
        userLocationService.save(new UserLocationVO(
                userLocationDTO.getUserId(),
                userLocationDTO.getLatitude(),
                userLocationDTO.getLongitude()
                )
        );
        return new ResponseEntity<>(userLocationDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{user_id}")
    public ResponseEntity<UserLocationDTO> update(@RequestBody UserLocationDTO userLocationDTO, @PathVariable Integer user_id) {
        userLocationService.save(new UserLocationVO(
                user_id,
                userLocationDTO.getLatitude(),
                userLocationDTO.getLongitude()
        ));
        return new ResponseEntity<>(userLocationDTO, HttpStatus.OK);
    }

}
