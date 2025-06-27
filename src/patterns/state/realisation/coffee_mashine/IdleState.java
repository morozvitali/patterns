package patterns.state.realisation.coffee_mashine;

public class IdleState implements State {
    public void pressButton (CoffeeMashine mashine) {
        System.out.println("Запускаємо приготування кави");
        mashine.setState(new BrewingState());
    }
}
