package petexplorer.salonrepos;

import org.springframework.stereotype.Component;
import petexplorer.Repository;
import petexplorer.domain.Salon;

@Component
public interface ISalonRepository extends Repository<Integer, Salon> {
}
