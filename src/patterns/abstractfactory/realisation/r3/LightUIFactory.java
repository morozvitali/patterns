package patterns.abstractfactory.realisation.r3;

public class LightUIFactory implements UIFactory{
    @Override
    public Button createButton() {
        return new LightButton();
    }
    @Override
    public Checkbox createCheckbox () {
        return new LigthCheckbox();
    }
}
