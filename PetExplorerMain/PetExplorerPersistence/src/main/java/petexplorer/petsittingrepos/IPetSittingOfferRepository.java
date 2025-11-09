package petexplorer.petsittingrepos;

import petexplorer.Repository;
import petexplorer.domain.PetSittingOffer;

public interface IPetSittingOfferRepository extends Repository<Integer, PetSittingOffer> {
    Iterable<PetSittingOffer> findAll(String location, String availability, Integer userId);
}


