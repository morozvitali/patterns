package patterns.composite.realisation;

public class File implements FileSystemComponent {
    private String name;

    public File (String name) {
        this.name = name;
    }

    @Override
    public void show () {
        System.out.println("File " + name);
    }
}
