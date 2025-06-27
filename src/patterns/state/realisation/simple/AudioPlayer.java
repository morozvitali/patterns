package patterns.state.realisation.simple;

public class AudioPlayer {
    private State state;

    public AudioPlayer () {
        this.state = new PausedState(); // початковий стан
    }

     public void setState (State state) {
        this.state = state;
     }

     public void play () {
        state.play(this);
     }

     public void pause () {
        state.pause(this);
     }



}

