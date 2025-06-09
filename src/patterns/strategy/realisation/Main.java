package patterns.strategy.realisation;

public class Main {
    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        calculator.setStrategy(new AddStrategy());
        System.out.println("Sum " + calculator.calculate(4,6));

        calculator.setStrategy(new SubStrategy());
        System.out.println("Sum " + calculator.calculate(4,6));

        calculator.setStrategy(new MultiplyStrategy());
        System.out.println("Sum " + calculator.calculate(4,6));
    }
}
