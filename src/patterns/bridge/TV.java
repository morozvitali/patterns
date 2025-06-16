package patterns.bridge;

public class TV implements Device {
    private boolean on = false;
    public void enable () {
        on = true;
        System.out.println("TV on");
    }

    public void disable () {
        on = false;
        System.out.println("TV off");
    }

    public boolean isEnabled () {
        return on;
    }
}