import java.util.Map;

public class Document {

    private final Map<String, String> attributes;

    Document(final Map<String, String> attributes) {
        this.attributes = attributes;
    }

    public Map<String, String> getAttribute() {
        return attributes;
    }

    @Override
    public String toString() {
        return "Document{" +
                "attributes=" + attributes +
                '}';
    }
}
