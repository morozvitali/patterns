package patterns.adapter1.realisation.printer3;

public class Main {
    public static void main(String[] args) {
        OldPrinter oldPrinter = new OldPrinter();
        NewPrinter printer = new PrintAdapter(oldPrinter);
        printer.print("hello adapter");
    }
}
