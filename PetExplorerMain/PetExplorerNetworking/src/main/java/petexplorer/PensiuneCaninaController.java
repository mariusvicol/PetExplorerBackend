package petexplorer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import petexplorer.domain.PensiuneCanina;
import petexplorer.domain.User;
import petexplorer.pensiunecaninarepos.IPensiuneCaninaRepository;
import petexplorer.userrepos.IUserRepository;


@RestController
@RequestMapping("/api/pensiuni")
public class PensiuneCaninaController {
    @Autowired
    protected IPensiuneCaninaRepository repository;
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
    public Iterable<PensiuneCanina> getAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public PensiuneCanina getById(@PathVariable Integer id) {
        return repository.findOne(id);
    }

    @PostMapping
    public PensiuneCanina save(@RequestBody PensiuneCanina pensiune, @RequestHeader Integer userId) {
        this.throwIfNotAdmin(userId);
        return repository.save(pensiune);
    }

    @PutMapping("/{id}")
    public PensiuneCanina update(@PathVariable Integer id, @RequestBody PensiuneCanina pensiuneNoua, @RequestHeader Integer userId) {
        this.throwIfNotAdmin(userId);
        pensiuneNoua.setId(id); // <-- aici
        repository.update(pensiuneNoua);
        return pensiuneNoua;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id, @RequestHeader Integer userId) {
        this.throwIfNotAdmin(userId);
        repository.delete(id);
    }
}


