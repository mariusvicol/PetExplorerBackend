package petexplorer.petsittingrepos;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;
import petexplorer.domain.PetSittingOffer;

import java.util.HashMap;
import java.util.Map;

@Component
public class PetSittingOfferRepository implements IPetSittingOfferRepository {

    private final SessionFactory sessionFactory;

    public PetSittingOfferRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public PetSittingOffer findOne(Integer id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(PetSittingOffer.class, id);
        }
    }

    @Override
    public Iterable<PetSittingOffer> findAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from PetSittingOffer order by createdAt desc", PetSittingOffer.class).list();
        }
    }

    @Override
    public Iterable<PetSittingOffer> findAll(String location, String availability, Integer userId) {
        try (Session session = sessionFactory.openSession()) {
            StringBuilder hql = new StringBuilder("from PetSittingOffer where 1=1");
            Map<String, Object> params = new HashMap<>();
            if (location != null && !location.isBlank()) {
                hql.append(" and lower(location) like :location");
                params.put("location", "%" + location.toLowerCase() + "%");
            }
            if (availability != null && !availability.isBlank()) {
                hql.append(" and lower(availability) like :availability");
                params.put("availability", "%" + availability.toLowerCase() + "%");
            }
            if (userId != null) {
                hql.append(" and userId = :userId");
                params.put("userId", userId);
            }
            hql.append(" order by createdAt desc");
            var query = session.createQuery(hql.toString(), PetSittingOffer.class);
            for (var entry : params.entrySet()) {
                query.setParameter(entry.getKey(), entry.getValue());
            }
            return query.list();
        }
    }

    @Override
    public PetSittingOffer save(PetSittingOffer entity) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.persist(entity);
            transaction.commit();
            return entity;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void delete(Integer id) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            PetSittingOffer offer = session.get(PetSittingOffer.class, id);
            if (offer != null) {
                session.delete(offer);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void update(PetSittingOffer entity) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.update(entity);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }
}


