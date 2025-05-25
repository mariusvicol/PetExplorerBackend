package petexplorer;

import org.hibernate.Session;
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

        List<Parc> parcuri = session
                .createQuery("from Parc", Parc.class)
                .getResultList();

        List<AnimalPierdut> animale = session
                .createQuery("from AnimalPierdut ", AnimalPierdut.class)
                .getResultList();

        List<LocatieFavorita> locatii = session
                .createQuery("from LocatieFavorita", LocatieFavorita.class)
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

        System.out.println("Toate pensiunile canine");
        for (PensiuneCanina p : pensiuniCanine) {
            System.out.println(p);
        }

        System.out.println("Toate saloanele");
        for (Salon salon : saloane) {
            System.out.println(salon);
        }

        System.out.println("Toate parcurile");
        for (Parc parc : parcuri) {
            System.out.println(parc);
        }

        System.out.println("Toate animalele pierdute");
        for (AnimalPierdut animal : animale) {
            System.out.println(animal);
        }

        System.out.println("Toate locatiile favorite");
        for (LocatieFavorita l : locatii) {
            System.out.println(l);
        }
    }
}