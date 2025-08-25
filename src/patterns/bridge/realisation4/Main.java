package patterns.bridge.realisation4;

public class Main {
    public static void main(String[] args) {
        Instrument guitar = new Guitar();
        Instrument drums = new Drums();

        SongPerformance show = new LiveShow(guitar);
        show.perform("Highway to Code");

        show.setInstrument(drums);
        show.perform("Highway to Code");

        show.setInstrument(new Sintesizer());
        show.perform("Highway to Code");
    }
}
