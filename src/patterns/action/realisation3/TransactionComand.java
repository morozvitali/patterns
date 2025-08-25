package patterns.action.realisation3;

public class TransactionComand implements Comand{
    private Comand comand;
    public TransactionComand (Comand comand) {
        this.comand = comand;
    }

    public void execute() {
        try {
            //beginTransaction();
            comand.execute();
            //commit();
        } catch (Exception e) {
            //rollback();
        }
    }

}
