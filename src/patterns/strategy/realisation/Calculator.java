package patterns.strategy.realisation;

public class Calculator {

    private OperationStrategy strategy;

    public void setStrategy (OperationStrategy strategy) {
        this.strategy = strategy;
    }

    public int calculate (int a, int b) {
        return strategy.execute(a,b);
    }
}

