package patterns.chainofrespons.realisation;

abstract class Logger {
    protected Logger next;

    public void setNext (Logger next) {
        this.next = next;
    }

    public void log (LogLevel level, String message) {
        if (canHandle (level)) {
            writeMessage(message);
        } else if (next != null) {
            next.log (level, message);
        }
    }

    protected abstract boolean canHandle (LogLevel level);
    protected abstract void writeMessage (String message);

}
