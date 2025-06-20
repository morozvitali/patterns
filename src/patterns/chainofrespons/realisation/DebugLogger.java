package patterns.chainofrespons.realisation;

public class DebugLogger extends Logger {
    protected boolean canHandle (LogLevel level) {
        return level == LogLevel.DEBUG;
    }

    protected void writeMessage (String message) {
        System.out.println("DEBUG " + message);
    }
}