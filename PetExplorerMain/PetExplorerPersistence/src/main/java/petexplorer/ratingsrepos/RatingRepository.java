package petexplorer.ratingsrepos;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;
import petexplorer.domain.Rating;
import petexplorer.domain.enums.LocationType;

import java.util.List;

@Component
public class RatingRepository implements IRatingRepository {

    private final SessionFactory sessionFactory;

    public RatingRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Rating findOne(Integer integer) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Rating.class, integer);
        }
    }

    @Override
    public Iterable<Rating> findAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from Rating", Rating.class).list();
        }
    }

    @Override
    public Rating save(Rating entity) {
        Transaction tr = null;
        try (Session session = sessionFactory.openSession()) {
            tr = session.beginTransaction();
            session.persist(entity);
            tr.commit();
            return entity;
        } catch (Exception e) {
            if (tr != null) {
                tr.rollback();
            }
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void delete(Integer integer) {
        Transaction tr = null;
        try (Session session = sessionFactory.openSession()) {
            tr = session.beginTransaction();
            Rating rating = session.get(Rating.class, integer);
            if (rating != null) {
                session.delete(rating);
            }
            tr.commit();
        } catch (Exception e) {
            if (tr != null) {
                tr.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void update(Rating entity) {
        Transaction tr = null;
        try (Session session = sessionFactory.openSession()) {
            tr = session.beginTransaction();
            session.update(entity);
            tr.commit();
        } catch (Exception e) {
            if (tr != null) {
                tr.rollback();
            }
            e.printStackTrace();
        }
    }

    public Rating findByUserAndLocation(Integer userId, LocationType locationType, Integer locationId) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery(
                            "from Rating where user.id = :userId and locationType = :locationType and locationId = :locationId",
                            Rating.class)
                    .setParameter("userId", userId)
                    .setParameter("locationType", locationType)
                    .setParameter("locationId", locationId)
                    .uniqueResult();
        }
    }

    public List<Rating> findByLocation(LocationType locationType, Integer locationId) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery(
                            "from Rating where locationType = :locationType and locationId = :locationId",
                            Rating.class)
                    .setParameter("locationType", locationType)
                    .setParameter("locationId", locationId)
                    .list();
        }
    }
}


