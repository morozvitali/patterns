package patterns.action.realisation4;

public class TransactionCommand implements Command {
    private Command command;

    public void TransactionComand(Command command) {
        this.command = command;
    }

    public void execute() {
        try {
            command.execute();
        } catch (Exception e) {
            //rollback
        }
    }
}
