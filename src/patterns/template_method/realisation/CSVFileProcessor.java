package patterns.template_method.realisation;

public class CSVFileProcessor extends FileProcessor {
    @Override
    protected String processContent (String content) {
        // уявимо що це CSV данні
        String[] lines = {"name,age", "Alice,30", "Bob,25"};

        StringBuilder sb = new StringBuilder("CSV Parsed:\n");

        for (String line : lines) {
            String [] cells = line.split(",");
            sb.append(" -> ");
            for (String cell : cells) {
                sb.append(cell).append(" | ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
