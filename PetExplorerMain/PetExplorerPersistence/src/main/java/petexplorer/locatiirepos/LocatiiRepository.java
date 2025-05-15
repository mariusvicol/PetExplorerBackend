package petexplorer.locatiirepos;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;
import petexplorer.domain.*;
import petexplorer.domain.enums.LocationType;

import java.util.List;

@Component
public class LocatiiRepository implements ILocatiiRepository {

    private final SessionFactory sessionFactory;

    public LocatiiRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public LocatieFavorita findOne(Integer integer) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(LocatieFavorita.class, integer);
        }
    }

    @Override
    public Iterable<LocatieFavorita> findAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from LocatieFavorita", LocatieFavorita.class).list();
        }
    }

    @Override
    public LocatieFavorita save(LocatieFavorita entity) {
        Transaction tr = null;

        try (Session session = sessionFactory.openSession()) {
            tr = session.beginTransaction();
            session.persist(entity);
            tr.commit();

            return entity;
        } catch (Exception e) {
            if (tr != null)
                tr.rollback();

            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void delete(Integer id) {
        Transaction tr = null;

        try (Session session = sessionFactory.openSession()) {
            tr = session.beginTransaction();
            LocatieFavorita pc = session.get(LocatieFavorita.class, id);

            if (pc != null) {
                session.delete(pc);
            }

            tr.commit();
        } catch (Exception e) {
            if (tr != null)
                tr.rollback();

            e.printStackTrace();
        }
    }

    @Override
    public void update(LocatieFavorita entity) {
        Transaction tr = null;

        try (Session session = sessionFactory.openSession()) {
            tr = session.beginTransaction();
            session.update(entity);
            tr.commit();
        } catch (Exception e) {
            if (tr != null)
                tr.rollback();

            e.printStackTrace();
        }
    }

    @Override
    public Object getReferredEntity(LocatieFavorita entity) {
        try (Session session = sessionFactory.openSession()) {
            LocationType type = entity.getLocationType();
            Integer id = entity.getEntityId();

            return switch (type) {
                case SALON -> session.get(Salon.class, id);
                case PARC -> session.get(Parc.class, id);
                case PENSIUNE -> session.get(PensiuneCanina.class, id);
                case CABINET -> session.get(CabinetVeterinar.class, id);
                case MAGAZIN -> session.get(Magazin.class, id);
                case FARMACIE -> session.get(Farmacie.class, id);
                default -> null;
            };

        }
    }

    @Override
    public List<LocatieFavorita> getLocationsForUser(Integer userId) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from LocatieFavorita where user.id = :id", LocatieFavorita.class)
                    .setParameter("id", userId)
                    .list();
        }
    }

    @Override
    public LocatieFavorita findByUserAndLocation(Integer userId, Integer locationId, String locationType) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from LocatieFavorita where user.id = :userId and entityId = :locationId and lower(locationType) = :locationType", LocatieFavorita.class)
                    .setParameter("userId", userId)
                    .setParameter("locationId", locationId)
                    .setParameter("locationType", locationType)
                    .uniqueResult();
        }
    }
}
