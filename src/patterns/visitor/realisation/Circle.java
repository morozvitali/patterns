package patterns.visitor.realisation;

public class Circle implements Shape{
    double radius = 5;

    @Override
    public void accept (ShapeVisitor visitor) {
        visitor.visit (this);
    }
}
