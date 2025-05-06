package petexplorer.userrepos;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;
import petexplorer.domain.CabinetVeterinar;
import petexplorer.domain.User;

@Component
public class UserRepository implements IUserRepository {
    private final SessionFactory sessionFactory;

    public UserRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public User findOne(Integer integer) {
        try(var session = sessionFactory.openSession()) {
            return session.get(User.class, integer);
        }
    }

    @Override
    public Iterable<User> findAll() {
        try(var session = sessionFactory.openSession()) {
            return session.createQuery("from User", User.class).list();
        }
    }

    @Override
    public User save(User entity) {
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
            User user = session.get(User.class, integer);
            if (user != null) {
                session.delete(user);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }

    }

    @Override
    public void update(User entity) {
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

    public User findByEmail(String email) {
        try (var session = sessionFactory.openSession()) {
            return session.createQuery("FROM User WHERE email = :email", User.class)
                    .setParameter("email", email)
                    .uniqueResult();
        }
    }

}
