package patterns.decorator.realisation.one;

public class Main {
    public static void main(String[] args) {
        Notifier notifier = new SMSDecorator(new EmailNotifier());
        notifier.send("Hello");
    }
}
