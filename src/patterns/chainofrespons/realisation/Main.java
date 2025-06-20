package patterns.chainofrespons.realisation;

public class Main {
    public static void main(String[] args) {
        Logger error = new ErrorLogger();
        Logger debug = new DebugLogger();
        Logger info = new InfoLogger();

        info.setNext(debug);
        debug.setNext(error);

        info.log(LogLevel.INFO, "Everything is fine.");
        info.log(LogLevel.DEBUG, "Debugging app.");
        info.log(LogLevel.ERROR, "Something went wrong!");
    }
}