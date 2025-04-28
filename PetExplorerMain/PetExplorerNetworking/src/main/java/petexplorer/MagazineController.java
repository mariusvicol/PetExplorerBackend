package petexplorer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import petexplorer.domain.Magazin;
import petexplorer.magazinerepos.IMagazinVeterinarRepository;

@RestController
@RequestMapping("/api/magazine")
public class MagazineController {
    @Autowired
    private IMagazinVeterinarRepository repo;

    @GetMapping
    public Iterable<Magazin> getAll() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public Magazin getById(@PathVariable Integer id) {
        return repo.findOne(id);
    }

    @PostMapping
    public Magazin create(@RequestBody Magazin farmacie) {
        return repo.save(farmacie);
    }

    @PutMapping("/{id}")
    public Magazin update(@PathVariable Integer id, @RequestBody Magazin farmacie) {
        farmacie.setId(id);
        repo.update(farmacie);
        return farmacie;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        repo.delete(id);
    }
}
