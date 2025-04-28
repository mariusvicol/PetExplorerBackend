package petexplorer;

import org.springframework.web.bind.annotation.*;
import petexplorer.domain.CabinetVeterinar;
import petexplorer.cabinetveterinarrepos.CabinetVeterinarRepository;

import java.util.List;

@RestController
@RequestMapping("/api/cabinete")
public class CabinetVeterinarController {

    private final CabinetVeterinarRepository cabinetVeterinarRepository;

    public CabinetVeterinarController(CabinetVeterinarRepository cabinetVeterinarRepository) {
        this.cabinetVeterinarRepository = cabinetVeterinarRepository;
    }

    @GetMapping("/{id}")
    public CabinetVeterinar getCabinetById(@PathVariable Integer id) {
        return cabinetVeterinarRepository.findOne(id);
    }

    @GetMapping
    public List<CabinetVeterinar> getAllCabinete() {
        return (List<CabinetVeterinar>) cabinetVeterinarRepository.findAll();
    }

    @PostMapping
    public CabinetVeterinar createCabinet(@RequestBody CabinetVeterinar cabinetVeterinar) {
        return cabinetVeterinarRepository.save(cabinetVeterinar);
    }

    @PutMapping("/{id}")
    public CabinetVeterinar updateCabinet(@PathVariable Integer id, @RequestBody CabinetVeterinar cabinetDetails) {
        CabinetVeterinar cabinetVeterinar = cabinetVeterinarRepository.findOne(id);
        if (cabinetVeterinar == null) {
            throw new RuntimeException("Cabinet not found with id " + id);
        }
        cabinetVeterinar.setLatitudine(cabinetDetails.getLatitudine());
        cabinetVeterinar.setLongitudine(cabinetDetails.getLongitudine());
        cabinetVeterinar.setNumeCabinet(cabinetDetails.getNumeCabinet());
        cabinetVeterinar.setNrTelefon(cabinetDetails.getNrTelefon());
        cabinetVeterinar.setNonStop(cabinetDetails.getNonStop());

        cabinetVeterinarRepository.update(cabinetVeterinar);
        return cabinetVeterinar;
    }

    @DeleteMapping("/{id}")
    public void deleteCabinet(@PathVariable Integer id) {
        cabinetVeterinarRepository.delete(id);
    }
}
