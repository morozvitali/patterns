package patterns.chainofrespons.realisation;

public class ErrorLogger extends Logger {
    protected boolean canHandle (LogLevel level) {
        return level == LogLevel.ERROR;
    }

    protected void writeMessage (String message) {
        System.out.println("Error " + message);
    }
}
