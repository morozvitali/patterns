package patterns.state.realisation.simple;

public class PausedState implements State {
    public void play (AudioPlayer player) {
        System.out.println("Відтворення відновлено");
        player.setState(new PlayingState());
    }

    public void pause (AudioPlayer player) {
        System.out.println("Уже призупинено");
    }
}
