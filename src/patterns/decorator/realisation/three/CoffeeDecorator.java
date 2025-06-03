package patterns.decorator.realisation.three;

public abstract class CoffeeDecorator implements Coffee {
    protected Coffee decoratedCoffee;
    public CoffeeDecorator (Coffee coffee) {this.decoratedCoffee = coffee;}

    @Override
    public String getDesription () {
        return decoratedCoffee.getDesription();
    }

    @Override
    public double getCost() {
        return decoratedCoffee.getCost();
    }
}
