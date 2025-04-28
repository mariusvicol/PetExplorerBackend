package petexplorer.parcrepos;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;
import petexplorer.domain.Parc;

@Component
public class ParcRepository  implements IParcRepository {

    private final SessionFactory sessionFactory;

    public ParcRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    @Override
    public Parc findOne(Integer integer) {
        try (var session = sessionFactory.openSession()) {
            return session.get(Parc.class, integer);
        }
    }

    @Override
    public Iterable<Parc> findAll() {
        try (var session = sessionFactory.openSession()) {
            return session.createQuery("from Parc", Parc.class).list();
        }
    }

    @Override
    public Parc save(Parc entity) {
        try (var session = sessionFactory.openSession()) {
            var transaction = session.beginTransaction();
            session.persist(entity);
            transaction.commit();
            return entity;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void delete(Integer integer) {
        Transaction transaction = null;
        try (var session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Parc parc = session.get(Parc.class, integer);
            if (parc != null) {
                session.delete(parc);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void update(Parc entity) {
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
