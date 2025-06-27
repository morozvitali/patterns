package patterns.observer.realisation;

public class Main {

    public static void main(String[] args) {
        Subject news = new Subject();

        Observer alice = new ConcreteObserver("Аліса");
        Observer bob = new ConcreteObserver("Боб");

        news.attach(alice);
        news.attach(bob);

        news.notifyObservers("Нове оновлення версії Java!");
    }
}