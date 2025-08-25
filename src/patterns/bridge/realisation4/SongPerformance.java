package patterns.bridge.realisation4;

public abstract class SongPerformance {
    protected Instrument instrument;

    public SongPerformance (Instrument instrument) {
        this.instrument = instrument;
    }

    public void setInstrument (Instrument instrument) {
        this.instrument = instrument;
    }

    public abstract void perform (String songName);

}
