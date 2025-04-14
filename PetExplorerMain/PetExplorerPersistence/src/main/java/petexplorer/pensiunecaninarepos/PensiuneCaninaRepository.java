package petexplorer.pensiunecaninarepos;

import org.hibernate.Transaction;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import petexplorer.domain.PensiuneCanina;

public class PensiuneCaninaRepository implements IPensiuneCaninaRepository{

    private final SessionFactory sessionFactory;

    public PensiuneCaninaRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public PensiuneCanina findOne(Integer id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(PensiuneCanina.class, id);
        }
    }

    @Override
    public Iterable<PensiuneCanina> findAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from PensiuneCanina", PensiuneCanina.class).list();
        }
    }

    @Override
    public PensiuneCanina save(PensiuneCanina entity) {
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
            PensiuneCanina pc = session.get(PensiuneCanina.class, id);

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
    public void update(PensiuneCanina entity) {
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
