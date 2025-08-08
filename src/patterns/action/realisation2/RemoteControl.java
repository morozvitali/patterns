package patterns.action.realisation2;

public class RemoteControl {
    private Command command;
    void setCommand (Command command) {
        this.command = command;
    }

    void pressButton () {
        command.execute();
    }
}
