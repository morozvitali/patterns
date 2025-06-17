package patterns.composite.realisation;

public class Main {
    public static void main(String[] args) {
        File file1 = new File("text.docx");
        File file2 = new File("photo.jpeg");

        Directory documents = new Directory("Root");
        documents.add(file1);
        documents.add(file2);

        Directory root = new Directory("Root");
        root.add(documents);
        root.show();
    }
}
