package searchengine.controllers;

import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import searchengine.dto.indexing.IndexingResult;
import searchengine.dto.statistics.StatisticsResponse;
import searchengine.model.Page;
import searchengine.model.SiteModel;
import searchengine.repository.PageRepository;
import searchengine.services.LinkGetterWithFJPool;
import searchengine.services.SiteParseService;
import searchengine.services.StatisticsService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;


@RestController
@RequestMapping("/api")
public class ApiController {

    private final StatisticsService statisticsService;
    private final SiteParseService siteParseService;

    public ApiController(StatisticsService statisticsService, SiteParseService siteParseService, PageRepository pageRepository) {
        this.statisticsService = statisticsService;
        this.siteParseService = siteParseService;
    }

    @GetMapping("/statistics")
    public ResponseEntity<StatisticsResponse> statistics() {
        return ResponseEntity.ok(statisticsService.getStatistics());
    }

    @GetMapping("/startIndexing")
    public ResponseEntity<IndexingResult> indexingStart() {

        IndexingResult indexingResult = new IndexingResult();
        List<SiteModel> siteModelList = siteParseService.saveSites_toDB();

        for(SiteModel siteModel : siteModelList) {
            List <Page> pageList = siteParseService.saveAllPagesSite_toDB(siteModel);
            siteParseService.saveIndexingData_toDB(pageList);
        }






        if (siteModelList.size() == 0) {
            indexingResult.setResult(false);
            indexingResult.setError("Indexing has already started");
            return ResponseEntity.status(HttpStatus.LOCKED).body(indexingResult);
        }
        indexingResult.setResult(true);
        return ResponseEntity.ok(indexingResult);
    }
}
