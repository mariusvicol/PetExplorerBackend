package petexplorer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import petexplorer.utils.SearchResultDTO;

import java.util.List;

@RestController
@RequestMapping("/api")
public class SearchController {
    @Autowired
    private SearchSession searchSession;

    @GetMapping("/search")
    public List<SearchResultDTO> search(@RequestParam String text) {
        return searchSession.search(text);
    }


}
