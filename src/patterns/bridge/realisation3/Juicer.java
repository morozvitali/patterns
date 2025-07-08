package patterns.bridge.realisation3;

public abstract class Juicer {
    protected Fruit fruit;
    public Juicer (Fruit fruit) {
        this.fruit = fruit;
    }
    public abstract void makeJuice();
}
