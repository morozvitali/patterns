package patterns.abstractfactory.realisation.one;

public class Application {

    private final Button button;
    private final CheckBox checkbox;

    public Application (UIFactory factory) {
        this.button = factory.createButton();
        this.checkbox = factory.createCheckbox();
    }

    public void renderUI () {
        button.paint();
        checkbox.render();
    }


    public static void main(String[] args) {
        UIFactory factory = new DarkUIFactory();
        Application app = new Application(factory);
        app.renderUI();

    }

}
