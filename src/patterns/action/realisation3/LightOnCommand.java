package patterns.action.realisation3;

public class LightOnCommand implements Comand {
    private Light light;

    public LightOnCommand(Light light) {
        this.light = light;

    }

    public void execute() {
        light.turnOn();
    }
}
