package petexplorer;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import petexplorer.utils.SearchResultDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Component
public class SearchSession {
    private final SessionFactory sessionFactory;

    public SearchSession(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Async("searchTaskExecutor")
    @Cacheable(value = "searchQueries", key = "#text.toLowerCase()")
    public CompletableFuture<List<SearchResultDTO>> search(String text) {
        String searchText = "%" + text.toLowerCase() + "%";

        List<SearchResultDTO> results = new ArrayList<>();
        try (Session session = sessionFactory.openSession()) {
            String hql =
                    "select s.id, s.name, 'Salon', s.latitude, s.longitude, s.nrTel, s.non_stop from Salon s where lower(s.name) like :text " +
                            "union all " +
                            "select pc.id, pc.name, 'Pensiune Canină', pc.latitude, pc.longitude, pc.nrTel, pc.non_stop from PensiuneCanina pc where lower(pc.name) like :text " +
                            "union all " +
                            "select p.id, p.nume, 'Parc', p.latitudine, p.longitudine, cast('' as string), cast(false as boolean) from Parc p where lower(p.nume) like :text " +
                            "union all " +
                            "select cv.id, cv.nume_cabinet, 'Cabinet Veterinar', cv.latitudine, cv.longitudine, cv.nrTelefon, cv.non_stop from CabinetVeterinar cv where lower(cv.nume_cabinet) like :text " +
                            "union all " +
                            "select m.id, m.nume, 'Magazin', m.latitudine, m.longitudine, cast('' as string), m.non_stop from Magazin m where lower(m.nume) like :text " +
                            "union all " +
                            "select f.id, f.nume, 'Farmacie Veterinară', f.latitudine, f.longitudine, cast('' as string), f.non_stop from Farmacie f where lower(f.nume) like :text";

            List<Object[]> rows = session.createQuery(hql, Object[].class)
                    .setParameter("text", searchText)
                    .list();

            for (Object[] row : rows) {
                Integer id = (Integer) row[0];
                String title = (String) row[1];
                String type = (String) row[2];
                Float lat = (Float) row[3];
                Float lon = (Float) row[4];
                String phone = (String) row[5];
                Boolean nonStop = (Boolean) row[6];

                results.add(new SearchResultDTO(lat, lon, title, phone, nonStop, type, id));
            }
        }

        return CompletableFuture.completedFuture(results);
    }
}