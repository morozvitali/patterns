package patterns.factory.realisation.two;

public abstract class Dialog {
    public void renderWindow () {
        Button okButton = createButton();
        okButton.render();
    }
    public abstract Button createButton();
}
