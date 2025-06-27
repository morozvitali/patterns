package patterns.state.realisation.coffee_mashine;

public class BrewingState implements State {
    public void pressButton (CoffeeMashine mashine) {
        System.out.println("Кава готується, зачекайте");
        //  імітація закінчення приготування
        mashine.setState(new ReadyState());
    }
}