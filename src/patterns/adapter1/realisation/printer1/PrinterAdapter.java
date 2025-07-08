package patterns.adapter1.realisation.printer1;

public class PrinterAdapter implements NewPrinter {
    private OldPrinter oldPrinter;

    public PrinterAdapter (OldPrinter oldPrinter) {
        this.oldPrinter = oldPrinter;
    }

    @Override
    public void print (String text) {
        oldPrinter.printOld(text); // адаптація виклику
    }
}
