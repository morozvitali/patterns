package patterns.bridge.realisation2;
public class Radio implements Device {
    private boolean on = false;
    public void enable () {on = true;
        System.out.println("Radio on");}
    public void disable () {
        on = false;
        System.out.println("radio off");
    }
    public boolean isEnabled () {
        return on;
    }
}
