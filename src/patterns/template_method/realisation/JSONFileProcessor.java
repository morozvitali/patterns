package patterns.template_method.realisation;

public class JSONFileProcessor extends FileProcessor {

    @Override
    protected String processContent(String content) {
        String[] fakeJsonArray = {"{\"name\":\"Alice\"}", "{\"name\":\"Bob\"}"};
        return "Кількість об’єктів у JSON: " + fakeJsonArray.length;
    }
}