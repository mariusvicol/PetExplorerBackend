package petexplorer;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import petexplorer.utils.SearchResultWrapper;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class SearchController {
    @Autowired
    private SearchSession searchSession;

    @GetMapping("/search")
    public List<SearchResultWrapper> search(@RequestParam String text) {
        List<SearchResultWrapper> all = new ArrayList<>();

        List<Object[]> results = searchSession.search(text);
        for (Object[] result : results) {
            Integer id = (Integer) result[0];
            String name = (String) result[1];
            String type = (String) result[2];

            all.add(new SearchResultWrapper(name, type, id));
        }

        searchSession.convertEntity(all);
        return all;
    }

}
