package patterns.adapter1.realisation.printer2;

public class PrinterAdapter implements NewPrinter {
    private final OldPrinter oldPrinter;

    public PrinterAdapter (OldPrinter oldPrinter) {
        this.oldPrinter = oldPrinter;
    }

    @Override
    public void print(String text) {
        oldPrinter.printOld(text);
    }
}
