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
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

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
        CompletableFuture<List<SearchResultDTO>> resultsFuture = searchSession.search("anima");
        try {
            List<SearchResultDTO> results = resultsFuture.get();

            for (SearchResultDTO result : results) {
                System.out.println(result);
            }
        } catch (InterruptedException | ExecutionException e) {
            System.err.println("Error retrieving search results: " + e.getMessage());
            e.printStackTrace();
        }

        HibernateUtils.getSessionFactory().close();
    }
}

