package petexplorer;


import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import petexplorer.animalpierdutrepos.AnimalPierdutRepository;
import petexplorer.domain.AnimalPierdut;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;


@RestController
@RequestMapping("/api/animale_pierdute")
public class AnimalPierdutController {

    private final AnimalPierdutRepository repo;

    public AnimalPierdutController(AnimalPierdutRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public Iterable<AnimalPierdut> getAll() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public AnimalPierdut getById(@PathVariable Integer id) {
        return repo.findOne(id);
    }

    @PostMapping(consumes = {"multipart/form-data"})
    public AnimalPierdut create(
            @RequestParam("nume_animal") String nume,
            @RequestParam("descriere") String descriere,
            @RequestParam("latitudine") float lat,
            @RequestParam("longitudine") float lng,
            @RequestParam("tip_caz") String tipCaz,
            @RequestParam("nr_telefon") String nrTelefon,
            @RequestPart("imagine") MultipartFile imagine) throws IOException {

        String uploadDir = System.getProperty("user.dir") + File.separator + "uploads" + File.separator;
        String fileName = System.currentTimeMillis() + "_" + imagine.getOriginalFilename();
        File destFile = new File(uploadDir + fileName);
        destFile.getParentFile().mkdirs();

        imagine.transferTo(destFile);

        AnimalPierdut animal = new AnimalPierdut();
        animal.setNume_animal(nume);
        animal.setDescriere(descriere);
        animal.setLatitudine(lat);
        animal.setLongitudine(lng);
        animal.setTip_caz(tipCaz);
        animal.setNr_telefon(nrTelefon);
        animal.setPoza("/uploads/" + fileName);
        animal.setData_caz(LocalDateTime.now());

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


