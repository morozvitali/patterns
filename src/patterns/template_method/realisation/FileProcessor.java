package patterns.template_method.realisation;

public abstract class FileProcessor {
    public void execute () {
        String content = readFile();
        String processed = processContent (content);
        writeFile(processed);
    }

    protected String readFile () {
        System.out.println("File reading");
        return "data"; // Формально тут повертаються зчитані данні з файлу
    }

    protected abstract String processContent (String content);

    protected void writeFile (String content) {
        System.out.println("Запис даних що оброблено " + content);
    }

}
