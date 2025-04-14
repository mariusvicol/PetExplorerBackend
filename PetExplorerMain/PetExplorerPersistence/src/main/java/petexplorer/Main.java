package petexplorer;

import petexplorer.domain.Farmacie;
import petexplorer.domain.Magazin;
import petexplorer.domain.PensiuneCanina;
import petexplorer.domain.Salon;
import petexplorer.farmacierepos.FarmacieVeterinaraRepository;
import petexplorer.farmacierepos.IFarmacieVeterinaraRepository;
import petexplorer.magazinerepos.IMagazinVeterinarRepository;
import petexplorer.magazinerepos.MagazinVeterinarRepository;
import petexplorer.pensiunecaninarepos.IPensiuneCaninaRepository;
import petexplorer.pensiunecaninarepos.PensiuneCaninaRepository;
import petexplorer.salonrepos.ISalonRepository;
import petexplorer.salonrepos.SalonRepository;
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
        HibernateUtils.getSessionFactory().close();
    }
}

