
package petexplorer.animalpierdutrepos;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import petexplorer.domain.AnimalPierdut;

public class AnimalPierdutRepository implements IAnimalPierdutRepository {

    private final SessionFactory sessionFactory;

    public AnimalPierdutRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    @Override
    public AnimalPierdut findOne(Integer integer) {
        try (var session = sessionFactory.openSession()) {
            return session.get(AnimalPierdut.class, integer);
        }
    }

    @Override
    public Iterable<AnimalPierdut> findAll() {
        try (var session = sessionFactory.openSession()) {
            return session.createQuery("from AnimalPierdut", AnimalPierdut.class).list();
        }
    }

    @Override
    public AnimalPierdut save(AnimalPierdut entity) {
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
            AnimalPierdut animalPierdut = session.get(AnimalPierdut.class, integer);
            if (animalPierdut != null) {
                session.delete(animalPierdut);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void update(AnimalPierdut entity) {
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
