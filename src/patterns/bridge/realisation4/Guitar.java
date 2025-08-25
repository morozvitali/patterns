package patterns.bridge.realisation4;

public class Guitar implements Instrument {
    @Override
    public void play(String songName) {
        System.out.println("guitar play song " + songName);
    }
}
