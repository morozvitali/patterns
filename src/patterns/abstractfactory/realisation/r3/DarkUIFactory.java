package patterns.abstractfactory.realisation.r3;

public class DarkUIFactory implements UIFactory {
    @Override
    public Button createButton() {
        return new DarkButton();
    }
    @Override
    public Checkbox createCheckbox() {
        return new DarkCheckbox();
    }
}
