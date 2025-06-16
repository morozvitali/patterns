package patterns.bridge;

public class AdvancedRemote extends Remote {
    public AdvancedRemote (Device device) {
        super (device);
    }

    public void mute () {
        System.out.println("Muting the device.");
    }
}
