package searchengine.controllers;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import searchengine.dto.indexing.IndexingResult;
import searchengine.dto.indexing.SearchData;
import searchengine.dto.statistics.StatisticsResponse;
import searchengine.repository.PageRepository;
import searchengine.services.IndexingSitesThread;
import searchengine.services.SiteParseService;
import searchengine.services.StatisticsService;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CancellationException;
import java.util.concurrent.FutureTask;


@Log4j2
@RestController
@RequestMapping("/api")
public class ApiController {

    private final StatisticsService statisticsService;
    private final SiteParseService siteParseService;
    static volatile boolean startIndexing = false;
    static FutureTask ft;

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
        if (startIndexing) {
            indexingResult.setResult(false);
            indexingResult.setError("Индексация уже запущена");
            return ResponseEntity.ok(indexingResult);
        }
        startIndexing = true;
        ft = new FutureTask<>(new IndexingSitesThread(siteParseService), "startIndexing");
        synchronized (ft) {
            new Thread(ft).start();
            try {
                ft.get();
                if (ft.isCancelled()) {
                    log.info("RUN() method is cancell");
                    startIndexing = false;
                    return ResponseEntity.ok(new IndexingResult(false, "Операция индексации прервана!"));
                }
            } catch (CancellationException | InterruptedException t) {
                log.info("Error when waiting for indexing thread to end:  " + t);
                t.printStackTrace();
                startIndexing = false;
                return ResponseEntity.ok(new IndexingResult(false, "Операция индексации прервана!"));
            } catch (Exception e) {
                log.error("Error when waiting for indexing thread to end:  " + e);
                e.printStackTrace();
                startIndexing = false;
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new IndexingResult(false, "The 'startIndexing' task failed"));
            }
            indexingResult.setResult(true);
            startIndexing = false;
            return ResponseEntity.ok(indexingResult);
        }
    }

    @GetMapping("/stopIndexing")
    public ResponseEntity<IndexingResult> indexingStop() {
        boolean checkIt;
            try {
                checkIt = ft.cancel(true);
            } catch (NullPointerException npe) {
                log.info("When the GET controller " +
                        "'stopIndexing' is accessed, the result of checking the flow of the site indexing is: " + npe);
                return ResponseEntity.ok(new IndexingResult(false, "Индексация не запущена"));
            }
            if (checkIt) {
                log.info("The 'stopIndexing' method is called, checkIt = " + checkIt);
                return ResponseEntity.ok(new IndexingResult(true, null));
            } else {
                log.info("the 'stopIndexing' task failed");
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new IndexingResult(false, "The 'stopIndexing' task failed"));
            }
    }

    @PostMapping("/indexPage")
    public ResponseEntity<IndexingResult> indexPage(@RequestParam String url) {
        IndexingResult indexingResult = siteParseService.indexingOnePage(url);
        return ResponseEntity.ok(indexingResult);
    }


    @GetMapping(value = "/search", params = "query")
    public ResponseEntity<?> resultOfSearch(@RequestParam String query) {
        try {
            List<SearchData> searchResult = siteParseService.getSearchResult(query);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }






}



