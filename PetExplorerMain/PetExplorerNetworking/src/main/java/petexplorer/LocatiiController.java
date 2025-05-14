package petexplorer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import petexplorer.domain.*;
import petexplorer.locatiirepos.ILocatiiRepository;
import petexplorer.locatiirepos.LocatiiRepository;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/locatii")
public class LocatiiController {
    @Autowired
    protected LocatiiRepository repository;

    public LocatiiController(LocatiiRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public Iterable<LocatieFavorita> getAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public LocatieFavorita getById(@PathVariable Integer id) {
        return repository.findOne(id);
    }

    @PostMapping
    public LocatieFavorita save(@RequestBody LocatieFavorita locatieFavorita) {
        return repository.save(locatieFavorita);
    }

    @PutMapping("/{id}")
    public LocatieFavorita update(@PathVariable Integer id, @RequestBody LocatieFavorita locatieFavorita) {
        locatieFavorita.setId(id);
        repository.update(locatieFavorita);
        return locatieFavorita;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        repository.delete(id);
    }

    @GetMapping("/{id}/reference")
    public Object getReferredEntity(@PathVariable Integer id) {
        LocatieFavorita locatieFavorita = repository.findOne(id);
        if (locatieFavorita == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Location entity not found");
        }

        return repository.getReferredEntity(locatieFavorita);
    }

    @GetMapping("/favorites/{userId}")
    public List<Object> getFavoriteLocations(@PathVariable Integer userId) {
        List<LocatieFavorita> favorite = repository.getLocationsForUser(userId);
        return favorite.stream()
                .map(loc -> {
                    Object entity = repository.getReferredEntity(loc);

                    if (entity instanceof Magazin m) {
                        return new LocatieFavoritaDTO(m.getLatitudine(), m.getLongitudine(), m.getNume(), loc.getLocationType().toString(), m.getNon_stop(), null);
                    } else if (entity instanceof Salon s) {
                        return new LocatieFavoritaDTO(s.getLatitude(), s.getLongitude(), s.getName(), loc.getLocationType().toString(), s.getNon_stop(), s.getNrTel());
                    } else if (entity instanceof CabinetVeterinar cv) {
                        return new LocatieFavoritaDTO(cv.getLatitudine(), cv.getLongitudine(), cv.getNumeCabinet(), loc.getLocationType().toString(), cv.getNonStop(), cv.getNrTelefon());
                    } else if (entity instanceof PensiuneCanina pc) {
                        return new LocatieFavoritaDTO(pc.getLatitude(), pc.getLongitude(), pc.getName(), loc.getLocationType().toString(), pc.getNon_stop(), pc.getNrTel());
                    } else if (entity instanceof Parc p) {
                        return new LocatieFavoritaDTO(p.getLatitudine(), p.getLongitudine(), p.getNume(), loc.getLocationType().toString(), p.getNonStop(), null);
                    } else if (entity instanceof Farmacie f) {
                        return new LocatieFavoritaDTO(f.getLatitudine(), f.getLongitudine(), f.getNume(), loc.getLocationType().toString(), f.getNon_stop(), null);
                    }

                    return entity;

                })
                .filter(Objects::nonNull)
                .toList();
    }
}
