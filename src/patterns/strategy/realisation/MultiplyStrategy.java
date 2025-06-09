package patterns.strategy.realisation;

public class MultiplyStrategy implements OperationStrategy {
    public int execute (int a, int b) {
        return a*b;
    }
}
