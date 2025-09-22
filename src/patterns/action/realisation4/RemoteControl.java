package patterns.action.realisation4;

public class RemoteControl {
private Command command;
void setCommand(Command command) {
    this.command = command;
}

void pressButton () {
    command.execute();
}

}
