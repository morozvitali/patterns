package patterns.factory.realisation.one;

public class ToyFactory {
    public static Toy createToy (String type) {
        return switch (type.toLowerCase()) {
            case "bear" -> new Bear ();
            case "robot" -> new Robot ();
            case "doll" -> new Doll ();
            default -> throw new IllegalArgumentException("Unknown toy: " + type);
        };
    }
}
