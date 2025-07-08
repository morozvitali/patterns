package patterns.bridge.realisation3;
public class ManualJuicer extends Juicer {
    public ManualJuicer (Fruit fruit) {
        super (fruit);
    }
    @Override
    public void makeJuice () {
        System.out.println("manual juicer get " + fruit.getJuice());
    }
}
