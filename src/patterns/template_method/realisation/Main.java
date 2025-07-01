package patterns.template_method.realisation;

public class Main {
    public static void main(String[] args) {

        FileProcessor csvFileProcessor = new CSVFileProcessor();
        csvFileProcessor.execute();
        System.out.println("---------------");
        FileProcessor jsonProcessor = new JSONFileProcessor();
        jsonProcessor.execute();
    }
}
