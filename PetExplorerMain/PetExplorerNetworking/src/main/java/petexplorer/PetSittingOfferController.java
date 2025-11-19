package petexplorer;

import org.springframework.web.bind.annotation.*;
import petexplorer.domain.PetSittingOffer;
import petexplorer.petsittingrepos.PetSittingOfferRepository;

@RestController
@RequestMapping("/api/petsitting")
public class PetSittingOfferController {

    private final PetSittingOfferRepository repository;

    public PetSittingOfferController(PetSittingOfferRepository repository) {
        this.repository = repository;
    }

    @GetMapping
	public Iterable<PetSittingOffer> getAll(@RequestParam(required = false) String location,
                                            @RequestParam(required = false) String availability,
											@RequestParam(required = false) Integer userId) {
		if ((location != null && !location.isBlank()) || (availability != null && !availability.isBlank()) || userId != null) {
			return repository.findAll(location, availability, userId);
		}
		return repository.findAll();
    }

    @GetMapping("/{id}")
    public PetSittingOffer getById(@PathVariable Integer id) {
        return repository.findOne(id);
    }

    @PostMapping
    public PetSittingOffer create(@RequestBody PetSittingOffer offer) {
        return repository.save(offer);
    }

    @PutMapping("/{id}")
    public PetSittingOffer update(@PathVariable Integer id, @RequestBody PetSittingOffer offer) {
        offer.setId(id);
        PetSittingOffer existing = repository.findOne(id);
        if (existing == null) return null;
        if (offer.getUserId() == null || !offer.getUserId().equals(existing.getUserId())) {
            throw new RuntimeException("Doar creatorul poate edita oferta.");
        }
        repository.update(offer);
        return offer;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id, @RequestParam("userId") Integer userId) {
        PetSittingOffer existing = repository.findOne(id);
        if (existing == null) return;
        if (!existing.getUserId().equals(userId)) {
            throw new RuntimeException("Doar creatorul poate sterge oferta.");
        }
        repository.delete(id);
    }
}


