package patterns.visitor.realisation;

public class Rectangle implements Shape {
    double width = 10, heigth = 4;
    @Override
    public void accept (ShapeVisitor visitor) {
        visitor.visit(this);
    }
}