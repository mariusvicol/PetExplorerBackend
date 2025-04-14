package petexplorer;

import petexplorer.cabinetveterinarrepos.CabinetVeterinarRepository;
import petexplorer.cabinetveterinarrepos.ICabinetVeterinarRepository;
import petexplorer.domain.Farmacie;
import petexplorer.domain.Magazin;
<<<<<<< HEAD
import petexplorer.domain.PensiuneCanina;
import petexplorer.domain.Salon;
=======
import petexplorer.domain.User;
import petexplorer.domain.CabinetVeterinar;
>>>>>>> 8d6513cb7f331b35e38539ec75fecc0a1683b5f6
import petexplorer.farmacierepos.FarmacieVeterinaraRepository;
import petexplorer.farmacierepos.IFarmacieVeterinaraRepository;
import petexplorer.magazinerepos.IMagazinVeterinarRepository;
import petexplorer.magazinerepos.MagazinVeterinarRepository;
<<<<<<< HEAD
import petexplorer.pensiunecaninarepos.IPensiuneCaninaRepository;
import petexplorer.pensiunecaninarepos.PensiuneCaninaRepository;
import petexplorer.salonrepos.ISalonRepository;
import petexplorer.salonrepos.SalonRepository;
=======
import petexplorer.userrepos.IUserRepository;
import petexplorer.userrepos.UserRepository;
>>>>>>> 8d6513cb7f331b35e38539ec75fecc0a1683b5f6
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

<<<<<<< HEAD
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
=======
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

>>>>>>> 8d6513cb7f331b35e38539ec75fecc0a1683b5f6
        HibernateUtils.getSessionFactory().close();
    }
}

