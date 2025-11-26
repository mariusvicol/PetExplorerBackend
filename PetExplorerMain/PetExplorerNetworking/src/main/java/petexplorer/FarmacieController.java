package petexplorer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import petexplorer.domain.Farmacie;
import petexplorer.domain.User;
import petexplorer.farmacierepos.IFarmacieVeterinaraRepository;
import petexplorer.userrepos.IUserRepository;
import petexplorer.userrepos.UserRepository;

@RestController
@RequestMapping("/api/farmacii")
public class FarmacieController {
    @Autowired
    private IFarmacieVeterinaraRepository repo;
    @Autowired
    private IUserRepository userRepository;

    public void throwIfNotAdmin(Integer userId) {
        User user = userRepository.findOne(userId);
        if (user == null) {
            throw new RuntimeException("User not found");
        }
        if (!user.getRole().equals("ADMIN")) {
            throw new RuntimeException("Only admins can create cabinet veterinars");
        }
    }

    @GetMapping
    public Iterable<Farmacie> getAll() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public Farmacie getById(@PathVariable Integer id) {
        return repo.findOne(id);
    }

    @PostMapping
    public Farmacie create(@RequestBody Farmacie farmacie, @RequestHeader Integer userId) {
        this.throwIfNotAdmin(userId);
        return repo.save(farmacie);
    }

    @PutMapping("/{id}")
    public Farmacie update(@PathVariable Integer id, @RequestBody Farmacie farmacie, @RequestHeader Integer userId) {
        this.throwIfNotAdmin(userId);
        farmacie.setId(id);
        repo.update(farmacie);
        return farmacie;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id, @RequestHeader Integer userId) {
        this.throwIfNotAdmin(userId);
        repo.delete(id);
    }
}
