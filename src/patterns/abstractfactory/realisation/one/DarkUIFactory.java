package patterns.abstractfactory.realisation.one;

public class DarkUIFactory implements UIFactory{
    public Button createButton () {
        return new DarkButton();
    }

    public CheckBox createCheckbox () {
        return new DarkCheckbox();
    }
}
