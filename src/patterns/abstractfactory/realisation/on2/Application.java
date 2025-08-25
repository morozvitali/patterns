package patterns.abstractfactory.realisation.on2;

public class Application {
    private final Button button;
    private final Checkbox checkbox;

    public Application (UIFactory factory) {
        this.button = factory.createButton();
        this.checkbox = factory.createCheckbox();
    }

    public void renderUI () {
        button.paint();
        checkbox.render();
    }
}
