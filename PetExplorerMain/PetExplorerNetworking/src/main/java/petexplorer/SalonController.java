package petexplorer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import petexplorer.domain.Salon;
import petexplorer.domain.User;
import petexplorer.salonrepos.ISalonRepository;
import petexplorer.salonrepos.SalonRepository;
import petexplorer.userrepos.IUserRepository;


@RestController
@RequestMapping("/api/saloane")
public class SalonController {
    @Autowired
    protected SalonRepository repository;
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

    public SalonController(SalonRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public Iterable<Salon> getAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Salon getById(@PathVariable Integer id) {
        return repository.findOne(id);
    }

    @PostMapping
    public Salon save(@RequestBody Salon salon, @RequestHeader Integer userId) {
        this.throwIfNotAdmin(userId);
        return repository.save(salon);
    }

    @PutMapping("/{id}")
    public Salon update(@PathVariable Integer id, @RequestBody Salon salonNou, @RequestHeader Integer userId) {
        this.throwIfNotAdmin(userId);
        salonNou.setId(id);
        repository.update(salonNou);
        return salonNou;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id, @RequestHeader Integer userId) {
        this.throwIfNotAdmin(userId);
        repository.delete(id);
    }
}


