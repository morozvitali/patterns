package patterns.state.realisation.simple;

public interface State {
    void play (AudioPlayer player);
    void pause (AudioPlayer player);
}
