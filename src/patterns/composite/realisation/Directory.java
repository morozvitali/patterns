package patterns.composite.realisation;

import java.util.ArrayList;
import java.util.List;

public class Directory implements FileSystemComponent {

    private String name;
    private List<FileSystemComponent> children = new ArrayList<>();

    public Directory (String name) {
        this.name = name;
    }

    public void add (FileSystemComponent component) {
        children.add(component);
    }

    @Override
    public void show () {
        System.out.println("teka " + name);
        for (FileSystemComponent child : children) {
            child.show();
        }
    }
}
