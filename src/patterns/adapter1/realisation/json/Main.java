package patterns.adapter1.realisation.json;

public class Main {
    public static void main(String[] args) {
        String json = "{\"name\":\"Віталій\",\"age\":30}";
        JsonParser parser = new GsonAdapter();
        JsonService service = new JsonService(parser);

        service.printParsedJson(json);
    }

}
