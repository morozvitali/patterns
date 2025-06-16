package patterns.bridge;

public class Main {
    public static void main(String[] args) {
        Remote remote = new AdvancedRemote(new TV());
        remote.togglePower(); // tv on
        ((AdvancedRemote)remote).mute(); //muting the device
    }
}
