package petexplorer;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;
import petexplorer.utils.SearchResultWrapper;

import java.util.ArrayList;
import java.util.List;

@Component
public class SearchSession {
    private final SessionFactory sessionFactory;

    public SearchSession(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<Object[]> search(String text) {
        try (Session session = sessionFactory.openSession()) {
            String hql = "select s.id as id, s.name as name, 'Salon' as type from Salon s where lower(s.name) like :text " +
                    "union all " +
                    "select pc.id as id, pc.name as name, 'Pensiune Canina' as type from PensiuneCanina pc where lower(pc.name) like :text " +
                    "union all " +
                    "select p.id as id, p.nume as name, 'Parc' as type from Parc p where lower(p.nume) like :text " +
                    "union all " +
                    "select cv.id as id, cv.nume_cabinet as name, 'Cabinet' as type from CabinetVeterinar cv where lower(cv.nume_cabinet) like :text " +
                    "union all " +
                    "select m.id as id, m.nume as name, 'Magazin' as type from Magazin m where lower(m.nume) like :text " +
                    "union all " +
                    "select f.id as id, f.nume as name, 'Farmacie' as type from Farmacie f where lower(f.nume) like :text";

            return session.createQuery(hql, Object[].class)
                    .setParameter("text", "%" + text.toLowerCase() + "%")
                    .list();
        }
    }


    public void convertEntity(List<SearchResultWrapper> all) {
        try (Session session = sessionFactory.openSession()) {
            for (SearchResultWrapper wrapper : all) {
                Object entity = wrapper.getEntity(session);
                System.out.println("Entitate incarcata: " + entity);
            }
        }
    }
}
