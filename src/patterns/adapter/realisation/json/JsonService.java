package patterns.adapter.realisation.json;
import java.util.Map;

public class JsonService {
    private final JsonParser parser;

    public JsonService (JsonParser parser) {
        this.parser = parser;
    }

    public void printParsedJson (String json) {
        Map<String, Object> data = parser.parse(json);
        System.out.println("Parsed JSON: " + data);
    }
}