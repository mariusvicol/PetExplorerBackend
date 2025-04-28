package petexplorer.pensiunecaninarepos;

import org.springframework.stereotype.Component;
import petexplorer.Repository;
import petexplorer.domain.PensiuneCanina;

@Component
public interface IPensiuneCaninaRepository extends Repository<Integer, PensiuneCanina> {
}
