package patterns.bridge;

public class Radio implements Device {
    private boolean on = false;

    public void enable () {
        on = true;
        System.out.println("Radio on");}

    public void disable () {
        on = false;
        System.out.println("Radio off");
    }

    public boolean isEnabled() {
        return on;
    }
}
