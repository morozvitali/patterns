package patterns.bridge.realisation4;

public class Drums implements Instrument {
    @Override
    public void play(String songName) {
        System.out.println("drums play song " + songName);
    }


}