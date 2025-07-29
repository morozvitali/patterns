package patterns.singleton.realisation2;

    /*
    using of static block
     */

public class Singleton4 {
    private static final Singleton4 instance = new Singleton4();
    private Singleton4 () {
    }
    public static Singleton4 getInstance() {
        return instance;
    }
}

