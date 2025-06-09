package patterns.strategy.realisation;

public class SubStrategy implements OperationStrategy {
    public int execute(int a, int b) {
        return a - b;
    }
}
