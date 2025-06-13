package patterns.adapter.realisation.json;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.Map;

public class GsonAdapter implements JsonParser {
    private final Gson gson = new Gson();

    @Override
    public Map<String,Object> parse (String json) {
        return gson.fromJson (json, new TypeToken <Map<String,Object>>(){}.getType());
    }


}
