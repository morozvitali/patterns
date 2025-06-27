package patterns.state.realisation.coffee_mashine;

public class CoffeeMashine {

    private State state;

    public CoffeeMashine () {
        state = new IdleState();
    }

    public void setState (State state) {
        this.state = state;
    }

    public void pressButton () {
        state.pressButton(this);
    }
}
