package petexplorer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import petexplorer.domain.PensiuneCanina;
import petexplorer.pensiunecaninarepos.IPensiuneCaninaRepository;


@RestController
@RequestMapping("/api/pensiuni")
public class PensiuneCaninaController {
    @Autowired
    protected IPensiuneCaninaRepository repository;

    @GetMapping
    public Iterable<PensiuneCanina> getAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public PensiuneCanina getById(@PathVariable Integer id) {
        return repository.findOne(id);
    }

    @PostMapping
    public PensiuneCanina save(@RequestBody PensiuneCanina pensiune) {
        return repository.save(pensiune);
    }

    @PutMapping("/{id}")
    public PensiuneCanina update(@PathVariable Integer id, @RequestBody PensiuneCanina pensiuneNoua) {
        pensiuneNoua.setId(id); // <-- aici
        repository.update(pensiuneNoua);
        return pensiuneNoua;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        repository.delete(id);
    }
}


