package searchengine.dto.indexing;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FoundData {

    private String site;
    private String siteName;
    private String uri;
    private String title;
    private String snippet;
    private Double relevance;

}
