package petexplorer;

import org.hibernate.Session;
import org.hibernate.Transaction;
import petexplorer.domain.CabinetVeterinar;
import petexplorer.domain.User;
import petexplorer.utils.HibernateUtils;

import java.util.List;
public class Main {
    public static void main(String[] args) {
        Session session = HibernateUtils.getSessionFactory().openSession();

        List<User> users = session
                .createQuery("from User", User.class)
                .getResultList();

        List<CabinetVeterinar> cabinetVeterinars = session
                .createQuery("from CabinetVeterinar", CabinetVeterinar.class)
                .getResultList();

        session.close();

        System.out.println("Toți utilizatorii:");
        for (User user : users) {
            System.out.println(user);
        }

        System.out.println("Toate cabinetele veterinare:");
        for (CabinetVeterinar cabinetVeterinar : cabinetVeterinars) {
            System.out.println(cabinetVeterinar);
        }
    }
}