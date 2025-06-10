package patterns.singleton.realisation;

public class Singleton4 {
    /*
    using of static block
     */
    private static final Singleton4 instance = new Singleton4();
    private Singleton4 () {}
    public static Singleton4 getInstance() {return instance;}
}
