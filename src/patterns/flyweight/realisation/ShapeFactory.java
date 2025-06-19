package patterns.flyweight.realisation;

import java.util.HashMap;
import java.util.Map;

public class ShapeFactory {
    private static final Map<String, Shape> circleMap = new HashMap<>();

    public static Shape getCircle (String color) {
        return circleMap.computeIfAbsent(color, c -> new Circle(c));
    }
}
