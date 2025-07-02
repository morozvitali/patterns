package patterns.visitor.realisation;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List <Shape> shapes = List.of(new Circle(), new Rectangle());
        ShapeVisitor areaCalculator = new AreaCalculator();

        for (Shape shape : shapes) {
            shape.accept(areaCalculator);
        }
    }
}
