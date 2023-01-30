package searchengine.services;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import searchengine.config.Site;
import searchengine.config.SitesList;
import searchengine.model.SiteModel;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ForkJoinPool;

@Service
@RequiredArgsConstructor
public class SiteParse {

    private final SitesList sites;

    public List<Site> saveSites_toDB(){
        List<Site> sitesList = sites.getSites();
        for(Site site : sitesList){
            String urlSite = site.getUrl();
            SiteModel.Status status = SiteModel.Status.INDEXING;
            String last_error = "";
            String site_name = site.getName();
            SiteModel siteModel = new SiteModel();





            Set<String> allPagesSite = retrievePagesFromSite()
        }
    }












    public Set<String> retrievePagesFromSite(SiteModel site){
        List<String> resultList = new ForkJoinPool().invoke(new LinkGetterWithFJPool(site));
        Set<String> allPagesSite = cleanDuplicates(resultList);
        return allPagesSite;
    }


    public static Set<String> cleanDuplicates(Collection<String> collection) {
        Set<String> elements = new TreeSet<>();
        for (String element : collection) {
            elements.add(element);
        }
        return elements;
    }





}
