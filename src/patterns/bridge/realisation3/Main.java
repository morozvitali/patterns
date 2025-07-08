package patterns.bridge.realisation3;

public class Main {
    public static void main(String[] args) {
        Juicer juicer1 = new ElrctricJuicer(new Apple());
        Juicer juicer2 = new ManualJuicer(new Kiwi());
        Juicer juicer3 = new TurboJuicer(new Orange());

        juicer1.makeJuice();
        juicer2.makeJuice();
        juicer3.makeJuice();


    }

}
