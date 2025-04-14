package petexplorer;

import petexplorer.domain.Farmacie;
import petexplorer.domain.Magazin;
import petexplorer.farmacierepos.FarmacieVeterinaraRepository;
import petexplorer.farmacierepos.IFarmacieVeterinaraRepository;
import petexplorer.magazinerepos.IMagazinVeterinarRepository;
import petexplorer.magazinerepos.MagazinVeterinarRepository;
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
        HibernateUtils.getSessionFactory().close();
    }
}

