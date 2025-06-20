package patterns.chainofrespons.realisation;

public class InfoLogger extends Logger {
    protected boolean canHandle (LogLevel level) {
        return level == LogLevel.INFO;
    }

    protected void writeMessage (String message) {
        System.out.println("INFO: " + message);
    }
}
