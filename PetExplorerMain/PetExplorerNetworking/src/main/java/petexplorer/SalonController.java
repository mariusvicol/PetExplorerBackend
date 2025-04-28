package petexplorer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import petexplorer.domain.Salon;
import petexplorer.salonrepos.ISalonRepository;
import petexplorer.salonrepos.SalonRepository;


@RestController
@RequestMapping("/api/saloane")
public class SalonController {
    @Autowired
    protected ISalonRepository repository;

    public SalonController(ISalonRepository repository) {
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
    public Salon save(@RequestBody Salon salon) {
        return repository.save(salon);
    }

    @PutMapping("/{id}")
    public Salon update(@PathVariable Integer id, @RequestBody Salon salonNou) {
        salonNou.setId(id);
        repository.update(salonNou);
        return salonNou;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        repository.delete(id);
    }
}


