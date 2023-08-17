import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WithLombok {
    public int count;
    private String secret;
    private final String final_secret = "final secret";

    public WithLombok() {
    }
}
