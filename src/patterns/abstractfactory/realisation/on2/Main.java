package patterns.abstractfactory.realisation.on2;

public class Main {


    public static void main(String[] args) {
        UIFactory factory = new DarkUIFactory();
        Application app = new Application(factory);
        app.renderUI();
    }
}
