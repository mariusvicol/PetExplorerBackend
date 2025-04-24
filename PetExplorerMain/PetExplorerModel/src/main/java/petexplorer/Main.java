package petexplorer;

import org.hibernate.Session;
import org.hibernate.Transaction;
import petexplorer.domain.*;
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

        List<PensiuneCanina> pensiuniCanine = session
                .createQuery("from PensiuneCanina", PensiuneCanina.class)
                .getResultList();

        List<Salon> saloane = session
                .createQuery("from Salon", Salon.class)
                .getResultList();

//        List<AnimalPierdut> animale_pierdute = session
//                .createQuery("from AnimalPierdut", AnimalPierdut.class)
//                .getResultList();

        session.close();

        System.out.println("Toți utilizatorii:");
        for (User user : users) {
            System.out.println(user);
        }

        System.out.println("Toate cabinetele veterinare:");
        for (CabinetVeterinar cabinetVeterinar : cabinetVeterinars) {
            System.out.println(cabinetVeterinar);
        }

        System.out.println("Toate pensiunile canine");
        for (PensiuneCanina p : pensiuniCanine) {
            System.out.println(p);
        }

        System.out.println("Toate saloanele");
        for (Salon salon : saloane) {
            System.out.println(salon);
        }

//        System.out.println("Toate animalele pierdute");
//        for (AnimalPierdut animal : animale_pierdute) {
//            System.out.println(animal);
//        }
    }
}