package patterns.singleton.realisation2;

    /*
    Double cheked locking
     */

public class Singleton3 {
    private static volatile Singleton3 instance;

    private Singleton3 () {
    }

    public static Singleton3 getInstance() {
        if (instance == null) {
            instance = new Singleton3();
        }
        return instance;
    }
}
