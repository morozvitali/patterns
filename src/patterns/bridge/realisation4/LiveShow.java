package patterns.bridge.realisation4;

public class LiveShow  extends SongPerformance {
    public LiveShow (Instrument instrument) {
        super (instrument);
    }

    @Override
    public void perform (String songName) {
        System.out.println("live performance");
        instrument.play(songName);
    }
}
