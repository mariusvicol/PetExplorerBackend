package petexplorer.cabinetveterinarrepos;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import petexplorer.domain.CabinetVeterinar;

public class CabinetVeterinarRepository implements ICabinetVeterinarRepository {
    private final SessionFactory sessionFactory;
    public CabinetVeterinarRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    @Override
    public CabinetVeterinar findOne(Integer integer) {
        try (var session = sessionFactory.openSession()) {
            return session.get(CabinetVeterinar.class, integer);
        }
    }

    @Override
    public Iterable<CabinetVeterinar> findAll() {
        try (var session = sessionFactory.openSession()) {
            return session.createQuery("from CabinetVeterinar", CabinetVeterinar.class).list();
        }
    }

    @Override
    public CabinetVeterinar save(CabinetVeterinar entity) {
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
                CabinetVeterinar cabinetVeterinar = session.get(CabinetVeterinar.class, integer);
                if (cabinetVeterinar != null) {
                    session.delete(cabinetVeterinar);
                }
                transaction.commit();
            } catch (Exception e) {
                if (transaction != null) transaction.rollback();
                e.printStackTrace();
            }
    }

    @Override
    public void update(CabinetVeterinar entity) {
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
