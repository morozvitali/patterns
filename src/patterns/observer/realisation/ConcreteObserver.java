package patterns.observer.realisation;

public class ConcreteObserver implements Observer {
    private final String name;
    public ConcreteObserver (String name) {
        this.name = name;
    }

    public void update (String message) {
        System.out.println(name + " отримав повідомлення " + message);
    }
}
