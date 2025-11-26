package petexplorer;

import org.springframework.web.bind.annotation.*;
import petexplorer.domain.CabinetVeterinar;
import petexplorer.cabinetveterinarrepos.CabinetVeterinarRepository;
import petexplorer.domain.User;
import petexplorer.userrepos.UserRepository;

import java.util.List;

@RestController
@RequestMapping("/api/cabinete")
public class CabinetVeterinarController {

    private final CabinetVeterinarRepository cabinetVeterinarRepository;
    private final UserRepository userRepository;

    public CabinetVeterinarController(CabinetVeterinarRepository cabinetVeterinarRepository, UserRepository userRepository) {
        this.cabinetVeterinarRepository = cabinetVeterinarRepository;
        this.userRepository = userRepository;
    }

    public void throwIfNotAdmin(Integer userId) {
        User user = userRepository.findOne(userId);
        if (user == null) {
            throw new RuntimeException("User not found");
        }
        if (!user.getRole().equals("ADMIN")) {
            throw new RuntimeException("Only admins can create cabinet veterinars");
        }
    }

    @GetMapping("/{id}")
    public CabinetVeterinar getCabinetById(@PathVariable Integer id) {
        return cabinetVeterinarRepository.findOne(id);
    }

    @GetMapping
    public List<CabinetVeterinar> getAllCabinete() {
        return (List<CabinetVeterinar>) cabinetVeterinarRepository.findAll();
    }

    @PostMapping
    public CabinetVeterinar createCabinet(@RequestBody CabinetVeterinar cabinetVeterinar, @RequestHeader("userId") Integer userId) {
        this.throwIfNotAdmin(userId);
        return cabinetVeterinarRepository.save(cabinetVeterinar);
    }

    @PutMapping("/{id}")
    public CabinetVeterinar updateCabinet(@PathVariable Integer id, @RequestBody CabinetVeterinar cabinetDetails, @RequestHeader("userId") Integer userId) {
        this.throwIfNotAdmin(userId);
        CabinetVeterinar cabinetVeterinar = cabinetVeterinarRepository.findOne(id);
        if (cabinetVeterinar == null) {
            throw new RuntimeException("Cabinet not found with id " + id);
        }
        cabinetVeterinar.setLatitudine(cabinetDetails.getLatitudine());
        cabinetVeterinar.setLongitudine(cabinetDetails.getLongitudine());
        cabinetVeterinar.setNumeCabinet(cabinetDetails.getNumeCabinet());
        cabinetVeterinar.setNrTelefon(cabinetDetails.getNrTelefon());
        cabinetVeterinar.setNonStop(cabinetDetails.getNonStop());

        cabinetVeterinarRepository.update(cabinetVeterinar);
        return cabinetVeterinar;
    }

    @DeleteMapping("/{id}")
    public void deleteCabinet(@PathVariable Integer id, @RequestHeader("userId") Integer userId) {
        this.throwIfNotAdmin(userId);
        cabinetVeterinarRepository.delete(id);
    }
}
