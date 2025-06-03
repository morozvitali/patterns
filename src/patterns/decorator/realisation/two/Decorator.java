package patterns.decorator.realisation.two;

public class Decorator implements Component {
    protected Component component;
    public Decorator (Component component) {
    }
    @Override
    public void operation () {
            component.operation(); // base operation
        System.out.println("Decorator operation");
    }
}