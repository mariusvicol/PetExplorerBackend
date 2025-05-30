package petexplorer;

import petexplorer.domain.Farmacie;
import petexplorer.domain.LocatieFavorita;
import petexplorer.domain.Magazin;
import petexplorer.farmacierepos.FarmacieVeterinaraRepository;
import petexplorer.farmacierepos.IFarmacieVeterinaraRepository;
import petexplorer.locatiirepos.ILocatiiRepository;
import petexplorer.locatiirepos.LocatiiRepository;
import petexplorer.magazinerepos.IMagazinVeterinarRepository;
import petexplorer.magazinerepos.MagazinVeterinarRepository;
import petexplorer.utils.HibernateUtils;
import petexplorer.utils.SearchResultDTO;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        IMagazinVeterinarRepository repo = new MagazinVeterinarRepository(HibernateUtils.getSessionFactory());
        IFarmacieVeterinaraRepository farmacieVeterinaraRepository=new FarmacieVeterinaraRepository(HibernateUtils.getSessionFactory());

        ILocatiiRepository locatiiRepository=new LocatiiRepository(HibernateUtils.getSessionFactory());

        System.out.println("Toate magazinele:");
        for (Magazin m : repo.findAll()) {
            System.out.println(m);
        }
        System.out.println("Toate farmacie:");
        for (Farmacie f : farmacieVeterinaraRepository.findAll()) {
            System.out.println(f);
        }

        System.out.println("Toate locatii:");
        for (LocatieFavorita l : locatiiRepository.findAll()) {
            System.out.println(l);
            System.out.println("Entitatea referita: " + locatiiRepository.getReferredEntity(l));
        }

        System.out.println("Rezultate cautare pentru 'anima':");
        SearchSession searchSession = new SearchSession(HibernateUtils.getSessionFactory());
        List<SearchResultDTO> results = searchSession.search("anima");
        for (SearchResultDTO result : results) {
            System.out.println(result);
        }

        HibernateUtils.getSessionFactory().close();
    }
}

