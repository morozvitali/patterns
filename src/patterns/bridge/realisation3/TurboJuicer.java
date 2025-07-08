package patterns.bridge.realisation3;
public class TurboJuicer extends Juicer {
    public TurboJuicer (Fruit fruit) {
        super (fruit);
    }
    @Override
    public void makeJuice () {
        System.out.println("turbo! " + fruit.getJuice());
    }
}
