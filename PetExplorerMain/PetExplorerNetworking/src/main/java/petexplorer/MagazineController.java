package petexplorer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import petexplorer.domain.Magazin;
import petexplorer.domain.User;
import petexplorer.magazinerepos.IMagazinVeterinarRepository;
import petexplorer.userrepos.IUserRepository;

@RestController
@RequestMapping("/api/magazine")
public class MagazineController {
    @Autowired
    private IMagazinVeterinarRepository repo;
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
    public Iterable<Magazin> getAll() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public Magazin getById(@PathVariable Integer id) {
        return repo.findOne(id);
    }

    @PostMapping
    public Magazin create(@RequestBody Magazin farmacie, @RequestHeader Integer userId) {
        this.throwIfNotAdmin(userId);
        return repo.save(farmacie);
    }

    @PutMapping("/{id}")
    public Magazin update(@PathVariable Integer id, @RequestBody Magazin farmacie, @RequestHeader Integer userId) {
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
