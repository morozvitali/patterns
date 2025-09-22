package patterns.abstractfactory.realisation.r3;

public class LightButton implements Button{
    @Override
    public void paint() {
        System.out.println("Ligth button");
    }
}
