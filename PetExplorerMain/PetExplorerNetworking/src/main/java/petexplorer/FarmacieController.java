package petexplorer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import petexplorer.domain.Farmacie;
import petexplorer.farmacierepos.IFarmacieVeterinaraRepository;

@RestController
@RequestMapping("/api/farmacii")
public class FarmacieController {
    @Autowired
    private IFarmacieVeterinaraRepository repo;

    @GetMapping
    public Iterable<Farmacie> getAll() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public Farmacie getById(@PathVariable Integer id) {
        return repo.findOne(id);
    }

    @PostMapping
    public Farmacie create(@RequestBody Farmacie farmacie) {
        return repo.save(farmacie);
    }

    @PutMapping("/{id}")
    public Farmacie update(@PathVariable Integer id, @RequestBody Farmacie farmacie) {
        farmacie.setId(id);
        repo.update(farmacie);
        return farmacie;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        repo.delete(id);
    }
}
