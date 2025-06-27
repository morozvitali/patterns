package patterns.state.realisation.simple;

public class Main {
    public static void main(String[] args) {
        AudioPlayer player = new AudioPlayer();
        player.play(); // Відтворення вілновлено
        player.pause(); // Призупинено
        player.pause(); // Вже призпинено
    }
}
