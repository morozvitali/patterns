package patterns.action.realisation2;

public class LightOnCommand {
    private Light light;
    LightOnCommand (Light light) {
        this.light = light;
    }
    public void execute () {
        light.turnOn();
    }
}
