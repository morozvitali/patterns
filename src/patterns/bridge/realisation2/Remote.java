package patterns.bridge.realisation2;
public abstract class Remote {
    protected Device device;
    public Remote(Device device) {
        this.device = device;
    }
    public void togglePower() {
        if (device.isEnabled()) device.disable();
        else device.enable();
    }
}
