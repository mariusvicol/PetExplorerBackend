package petexplorer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import petexplorer.domain.Parc;
import petexplorer.parcrepos.IParcRepository;


@RestController
@RequestMapping("/api/parcuri")
public class ParcController {
    @Autowired
    private IParcRepository repo;

    @GetMapping
    public Iterable<Parc> getAll() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public Parc getById(@PathVariable Integer id) {
        return repo.findOne(id);
    }

    @PostMapping
    public Parc create(@RequestBody Parc parc) {
        return repo.save(parc);
    }

    @PutMapping("/{id}")
    public Parc update(@PathVariable Integer id, @RequestBody Parc parc) {
        parc.setId(id);
        repo.update(parc);
        return parc;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        repo.delete(id);
    }
}
