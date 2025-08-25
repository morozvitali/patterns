package patterns.adapter1.realisation.printer3;

public class PrintAdapter implements NewPrinter{
    private OldPrinter oldPrinter; // old printer entity

    public PrintAdapter  (OldPrinter oldPrinter) {
        this.oldPrinter = oldPrinter;
    }

    @Override
    public void print (String text) {
        oldPrinter.printOld(text); // call adaptation
    }

}
