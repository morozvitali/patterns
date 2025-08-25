package patterns.action.realisation3;

public class RemoteControl {
    private Comand comand;
    void setComand (Comand comand) {
        this.comand = comand;
    }
    void pressButton () {
        comand.execute();
    }
}
