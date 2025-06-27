package patterns.state.realisation.simple;

public class PlayingState implements State {

    public void play (AudioPlayer player) {
        System.out.println("Вже відтворюється");
    }

    public void pause (AudioPlayer player) {
        System.out.println("Відтворення призупинено");
        player.setState(new PlayingState());
    }
}