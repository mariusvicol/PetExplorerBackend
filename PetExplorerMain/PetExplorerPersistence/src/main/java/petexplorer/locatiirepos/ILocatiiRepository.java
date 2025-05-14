package petexplorer.locatiirepos;

import org.springframework.stereotype.Component;
import petexplorer.Repository;
import petexplorer.domain.LocatieFavorita;

import java.util.List;

@Component
public interface ILocatiiRepository extends Repository<Integer, LocatieFavorita> {

    Object getReferredEntity(LocatieFavorita entity);
    List<LocatieFavorita> getLocationsForUser(Integer userId);
}
