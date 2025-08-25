package patterns.abstractfactory.realisation.on2;

public class LightUIFactory implements UIFactory {

    public Button createButton() {
        return new LightButton();
    }

    public Checkbox createCheckbox() {
        return new LightCheckbox();
    }
}
