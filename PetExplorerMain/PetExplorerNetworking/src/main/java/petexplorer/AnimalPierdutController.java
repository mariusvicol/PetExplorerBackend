package petexplorer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import petexplorer.animalpierdutrepos.IAnimalPierdutRepository;
import petexplorer.domain.AnimalPierdut;


@RestController
@RequestMapping("/api/animale_pierdute")
public class AnimalPierdutController {
    @Autowired
    private IAnimalPierdutRepository repo;

    @GetMapping
    public Iterable<AnimalPierdut> getAll() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public AnimalPierdut getById(@PathVariable Integer id) {
        return repo.findOne(id);
    }

    @PostMapping
    public AnimalPierdut create(@RequestBody AnimalPierdut animal) {
        return repo.save(animal);
    }

    @PutMapping("/{id}")
    public AnimalPierdut update(@PathVariable Integer id, @RequestBody AnimalPierdut animal) {
        animal.setId(id);
        repo.update(animal);
        return animal;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        repo.delete(id);
    }
}

