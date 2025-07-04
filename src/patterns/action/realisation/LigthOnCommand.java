package patterns.action.realisation;
public class LigthOnCommand implements Command {
    private Ligth ligth;
    LigthOnCommand(Ligth ligth) {
        this.ligth = ligth;
    }
    public void execute() {
        ligth.turnOn();
    }
}