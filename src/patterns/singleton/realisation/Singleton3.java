package patterns.singleton.realisation;

public class Singleton3 {
    private static volatile Singleton3 instance;
    /*
    Double cheked locking
     */
    private Singleton3 () {}
    public static Singleton3 getInstance() {
        if (instance == null) {
            synchronized (Singleton3.class) {
                if (instance == null) {
                    instance = new Singleton3();
                }
            }
        }
        return instance;
    }
}
