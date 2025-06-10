package patterns.singleton.realisation;

public class Singleton1 {
/*
 ледача реплізація, непотокобезпечна
 */
    private static Singleton1 instance;
    private Singleton1() {
    }
    public static Singleton1 getInstance() {
        if (instance == null) {
            instance = new Singleton1();
        }
        return instance;
    }
}
