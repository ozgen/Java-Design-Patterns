package singleton;

class InnerStaticSingleton {

    private InnerStaticSingleton() {
        System.out.println(InnerStaticSingleton.class.getSimpleName() + " is initialized.");
    }

    /**
     * this is also thread safe...
     */
    private static class Impl {
        private static InnerStaticSingleton INSTANCE = new InnerStaticSingleton();
    }

    public static InnerStaticSingleton getInstance() {
        return Impl.INSTANCE;
    }

}

public class InnerStaticSingletonDemo {

    public static void main(String[] args) {
        InnerStaticSingleton.getInstance();
    }
}
