package searchengine.services;

import lombok.SneakyThrows;
import searchengine.model.Page;
import searchengine.model.SiteModel;

import java.util.List;

public class IndexingSitesThread implements Runnable {

    private final SiteParseService siteParseService;

    public IndexingSitesThread(SiteParseService siteParseService) {
        this.siteParseService = siteParseService;
    }



    @SneakyThrows(InterruptedException.class)
    @Override
    public void run() {
        List<SiteModel> siteModelList = siteParseService.saveSites_toDB();
        for(SiteModel siteModel : siteModelList) {
            if (!Thread.currentThread().isInterrupted()) {
                List<Page> pageList = siteParseService.saveAllPagesSite_toDB(siteModel);
                siteParseService.saveIndexingData_toDB(pageList);
            }
            else {
                throw new InterruptedException ("Выполнение индексирования прервано!");
            }
        }
    }
}
