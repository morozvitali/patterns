package patterns.state.realisation.coffee_mashine;

import java.util.IdentityHashMap;

public class ReadyState implements State {
    public void pressButton (CoffeeMashine mashine) {
        System.out.println("Каву видано, смачного");
        mashine.setState(new IdleState());
    }
}
