package patterns.abstractfactory.realisation.r3;

public class DarkButton implements Button{
    @Override
    public void paint() {
        System.out.println("Dark button");
    }
}
