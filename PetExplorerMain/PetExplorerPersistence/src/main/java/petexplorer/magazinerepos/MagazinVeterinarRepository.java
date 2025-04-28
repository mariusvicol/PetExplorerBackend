package petexplorer.magazinerepos;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;
import petexplorer.domain.Magazin;
@Component
public class MagazinVeterinarRepository implements IMagazinVeterinarRepository {
    private final SessionFactory sessionFactory;
    public MagazinVeterinarRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Magazin findOne(Integer id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Magazin.class, id);
        }
    }

    @Override
    public Iterable<Magazin> findAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from Magazin", Magazin.class).list();
        }
    }

    @Override
    public Magazin save(Magazin entity) {
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
            Magazin magazin = session.get(Magazin.class, id);
            if (magazin != null) {
                session.delete(magazin);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void update(Magazin magazin) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.update(magazin);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }
}
