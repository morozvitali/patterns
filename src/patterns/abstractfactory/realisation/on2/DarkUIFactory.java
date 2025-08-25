package patterns.abstractfactory.realisation.on2;

public class DarkUIFactory implements UIFactory {
    public Button createButton() {
        return new DarkButton();
    }

    public Checkbox createCheckbox () {
        return new DarkCheckbox();
    }
}
