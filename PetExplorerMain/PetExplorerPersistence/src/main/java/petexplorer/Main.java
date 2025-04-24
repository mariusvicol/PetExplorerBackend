package petexplorer;

import petexplorer.animalpierdutrepos.AnimalPierdutRepository;
import petexplorer.animalpierdutrepos.IAnimalPierdutRepository;
import petexplorer.cabinetveterinarrepos.CabinetVeterinarRepository;
import petexplorer.cabinetveterinarrepos.ICabinetVeterinarRepository;
import petexplorer.domain.*;
import petexplorer.farmacierepos.FarmacieVeterinaraRepository;
import petexplorer.farmacierepos.IFarmacieVeterinaraRepository;
import petexplorer.magazinerepos.IMagazinVeterinarRepository;
import petexplorer.magazinerepos.MagazinVeterinarRepository;
import petexplorer.parcrepos.IParcRepository;
import petexplorer.parcrepos.ParcRepository;
import petexplorer.pensiunecaninarepos.IPensiuneCaninaRepository;
import petexplorer.pensiunecaninarepos.PensiuneCaninaRepository;
import petexplorer.salonrepos.ISalonRepository;
import petexplorer.salonrepos.SalonRepository;
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

        System.out.println("---------------------------\n");

        IPensiuneCaninaRepository pcRepo = new PensiuneCaninaRepository(HibernateUtils.getSessionFactory());
        ISalonRepository salonRepo = new SalonRepository(HibernateUtils.getSessionFactory());

        System.out.println("Toate pensiunile:");
        for (PensiuneCanina pc : pcRepo.findAll()) {
            System.out.println(pc);
        }

        System.out.println("Toate saloanele:");
        for (Salon s : salonRepo.findAll()) {
            System.out.println(s);
        }

        System.out.println("----------------------------\n");

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

        System.out.println("----------------------------\n");

        IAnimalPierdutRepository animalRepository = new AnimalPierdutRepository(HibernateUtils.getSessionFactory());
        System.out.println("Toate animalele pierdute:");
        for (AnimalPierdut animal : animalRepository.findAll()) {
            System.out.println(animal);
        }

        IParcRepository parcRepository = new ParcRepository(HibernateUtils.getSessionFactory());
        System.out.println("Toate parcurile:");
        for (Parc parc :parcRepository.findAll()) {
            System.out.println(parc);
        }

        HibernateUtils.getSessionFactory().close();
    }
}

