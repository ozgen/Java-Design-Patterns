package singleton;

class LazySingleton {
    private static LazySingleton instance;

    private LazySingleton() {
        System.out.println(LazySingleton.class.getSimpleName() + " is initialized.");
    }

    //for double check
    public static LazySingleton getInstance() {
        if (instance == null) {
            synchronized (LazySingleton.class) {
                instance = new LazySingleton();
            }
        }
        return instance;
    }

}

public class LazySingletonDemo {

    public static void main(String[] args) {

        LazySingleton.getInstance();
    }
}
