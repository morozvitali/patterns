package patterns.bridge.realisation3;

public class Apple implements Fruit {
    @Override
    public String getJuice() {
        return "Apple juice";
    }
}