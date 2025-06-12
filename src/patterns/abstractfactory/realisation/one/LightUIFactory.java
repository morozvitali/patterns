package patterns.abstractfactory.realisation.one;

public class LightUIFactory implements UIFactory {
    public Button createButton () {
        return new LightButton();
    }

    public CheckBox createCheckbox () {
        return new LightCheckbox();
    }
}
