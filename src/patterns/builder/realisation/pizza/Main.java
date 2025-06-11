package patterns.builder.realisation.pizza;

public class Main {
    public static void main(String[] args) {
        Pizza pizza = new Pizza.Builder()
                .setDough("this crust")
                .setSauce("pesto")
                .setTopping("mazarella")
                .build();
    }
}