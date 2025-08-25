package patterns.bridge.realisation4;

public class Sintesizer implements Instrument {
    @Override
    public void play(String songName) {
        System.out.println("sintesizer play song " + songName);
    }
}
