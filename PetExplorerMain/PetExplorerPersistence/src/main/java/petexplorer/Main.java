package petexplorer;

import petexplorer.cabinetveterinarrepos.CabinetVeterinarRepository;
import petexplorer.cabinetveterinarrepos.ICabinetVeterinarRepository;
import petexplorer.domain.Farmacie;
import petexplorer.domain.Magazin;
import petexplorer.domain.User;
import petexplorer.domain.CabinetVeterinar;
import petexplorer.farmacierepos.FarmacieVeterinaraRepository;
import petexplorer.farmacierepos.IFarmacieVeterinaraRepository;
import petexplorer.magazinerepos.IMagazinVeterinarRepository;
import petexplorer.magazinerepos.MagazinVeterinarRepository;
import petexplorer.userrepos.IUserRepository;
import petexplorer.userrepos.UserRepository;
import petexplorer.utils.HibernateUtils;

public class Main {
    public static void main(String[] args) {
        IMagazinVeterinarRepository repo = new MagazinVeterinarRepository(HibernateUtils.getSessionFactory());
        IFarmacieVeterinaraRepository farmacieVeterinaraRepository=new FarmacieVeterinaraRepository(HibernateUtils.getSessionFactory());
        System.out.println("Toate magazinele:");
        for (Magazin m : repo.findAll()) {
            System.out.println(m);
        }
        System.out.println("Toate farmacie:");
        for (Farmacie f : farmacieVeterinaraRepository.findAll()) {
            System.out.println(f);
        }

        IUserRepository userRepository = new UserRepository(HibernateUtils.getSessionFactory());
        System.out.println("Toți utilizatorii:");
        for (User user : userRepository.findAll()) {
            System.out.println(user);
        }

        ICabinetVeterinarRepository cabinetVeterinarRepository = new CabinetVeterinarRepository(HibernateUtils.getSessionFactory());
        System.out.println("Toate cabinetele veterinare:");
        for (CabinetVeterinar cabinetVeterinar : cabinetVeterinarRepository.findAll()) {
            System.out.println(cabinetVeterinar);
        }

        HibernateUtils.getSessionFactory().close();
    }
}

