package patterns.factory.realisation.two;

public class WinOsDialog extends Dialog {
    public Button createButton () {
        return new WinOsButton();
    }

}
