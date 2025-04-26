package petexplorer.salonrepos;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import petexplorer.domain.PensiuneCanina;
import petexplorer.domain.Salon;

public class SalonRepository implements ISalonRepository {
    private final SessionFactory sessionFactory;

    public SalonRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Salon findOne(Integer id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Salon.class, id);
        }
    }

    @Override
    public Iterable<Salon> findAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from Salon", Salon.class).list();
        }
    }

    @Override
    public Salon save(Salon entity) {
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
            Salon pc = session.get(Salon.class, id);

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
    public void update(Salon entity) {
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
}
