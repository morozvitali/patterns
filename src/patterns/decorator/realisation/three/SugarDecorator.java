package patterns.decorator.realisation.three;

public class SugarDecorator extends CoffeeDecorator {
    public SugarDecorator (Coffee coffee) {
        super(coffee);
    }

    public String getDescription () {
        return super.getDesription() + " , sugar";
    }

    public double getCost () {
        return super.getCost() + 2;
    }
}
