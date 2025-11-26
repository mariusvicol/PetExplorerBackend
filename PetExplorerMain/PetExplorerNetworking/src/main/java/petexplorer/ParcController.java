package petexplorer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import petexplorer.domain.Parc;
import petexplorer.domain.User;
import petexplorer.parcrepos.IParcRepository;
import petexplorer.parcrepos.ParcRepository;
import petexplorer.userrepos.IUserRepository;


@RestController
@RequestMapping("/api/parcuri")
public class ParcController {
    @Autowired
    private IParcRepository repo;
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
    public Iterable<Parc> getAll() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public Parc getById(@PathVariable Integer id) {
        return repo.findOne(id);
    }

    @PostMapping
    public Parc create(@RequestBody Parc parc, @RequestHeader Integer userId) {
        this.throwIfNotAdmin(userId);
        return repo.save(parc);
    }

    @PutMapping("/{id}")
    public Parc update(@PathVariable Integer id, @RequestBody Parc parc, @RequestHeader Integer userId) {
        this.throwIfNotAdmin(userId);
        parc.setId(id);
        repo.update(parc);
        return parc;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id, @RequestHeader Integer userId) {
        this.throwIfNotAdmin(userId);
        repo.delete(id);
    }
}
