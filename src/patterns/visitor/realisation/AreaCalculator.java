package patterns.visitor.realisation;

public class AreaCalculator implements ShapeVisitor {
    @Override
    public void visit (Circle c) {
        double area = Math.PI * c.radius * c.radius;
        System.out.println("Circle area: " + area);
    }
    @Override
    public void visit (Rectangle r) {
        double area = r.width * r.heigth;
        System.out.println("Rectangle area: " + area);
    }
}
