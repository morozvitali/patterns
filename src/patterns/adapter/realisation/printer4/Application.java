package patterns.adapter.realisation.printer4;

public class Application {
    public static void main(String[] args) {
        OldPrinter oldPrinter = new OldPrinter();
        NewPrinter printer = new PrinterAdapter(oldPrinter);
        printer.print("hello adapter");
    }
}
