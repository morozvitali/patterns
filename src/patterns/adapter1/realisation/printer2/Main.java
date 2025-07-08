package patterns.adapter1.realisation.printer2;

public class Main {
    public static void main(String[] args) {
        OldPrinter oldPrinter = new OldPrinter();
        NewPrinter printer = new PrinterAdapter(oldPrinter);
        printer.print("Привіт адапторе");
    }
}
