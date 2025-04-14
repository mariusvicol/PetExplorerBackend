package petexplorer.farmacierepos;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import petexplorer.domain.Farmacie;

public class FarmacieVeterinaraRepository implements IFarmacieVeterinaraRepository {
    private final SessionFactory sessionFactory;
    public FarmacieVeterinaraRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Farmacie findOne(Integer id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Farmacie.class, id);
        }
    }

    @Override
    public Iterable<Farmacie> findAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from Farmacie", Farmacie.class).list();
        }
    }

    @Override
    public Farmacie save(Farmacie entity) {
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
            Farmacie farmacie = session.get(Farmacie.class, id);
            if (farmacie != null) {
                session.delete(farmacie);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void update(Farmacie farmacie) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.update(farmacie);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }
}
