package patterns.decorator.realisation.three;

public class MilkDecorator extends CoffeeDecorator {
    public MilkDecorator(Coffee coffee) {
        super(coffee);
    }

    public String getDescription() {
        return super.getDesription() + ", milk";
    }

    public double getCost() {
        return super.getCost() + 3;
    }
}