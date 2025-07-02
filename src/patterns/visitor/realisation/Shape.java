package patterns.visitor.realisation;

interface Shape {
    void accept (ShapeVisitor visitor);
}
