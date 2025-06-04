package patterns.factory.realisation.two;

public class MacOsDialog extends Dialog{
    @Override
    public Button createButton() {
        return new MacOsButton();
    }
}
