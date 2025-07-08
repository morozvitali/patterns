package patterns.bridge.realisation3;

public class ElrctricJuicer extends Juicer {
    public ElrctricJuicer(Fruit fruit) {
        super(fruit);
    }

    @Override
    public void makeJuice() {
        System.out.println("electric juicer get " + fruit.getJuice());
    }
}
