package patterns.adapter.realisation.json;

import java.util.Map;

public interface JsonParser {
    Map<String, Object> parse (String json);
}