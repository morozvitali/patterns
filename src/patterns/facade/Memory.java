package patterns.facade;

public class Memory {
    public void load (long position, String data) {
        System.out.println("Loading data " + data + " into postion " + position);
    }
}
