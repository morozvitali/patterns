package patterns.flyweight.realisation;

public class FlyweightDemo {
    public static void main(String[] args) {
        for (int i = 0; i<10; i++) {
            Shape circle = ShapeFactory.getCircle("red");
            circle.draw(i,i*2);
        }
    }
}
