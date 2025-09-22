package patterns.action.realisation4;

public class LightOnCommand implements Command {
    private Light light;
    LightOnCommand(Light light) {
        this.light = light;
    }
    public void execute () {
        light.turnOn();
    }
}
