package nullobject;

import java.lang.reflect.Proxy;

interface Log {
    void info(String msg);

    void warn(String msg);
}

class ConsoleLog implements Log {

    @Override
    public void info(String msg) {
        System.out.println(msg);
    }

    @Override
    public void warn(String msg) {
        System.out.println("WARNING : " + msg);
    }
}

class NullLog implements Log {

    @Override
    public void info(String msg) {

    }

    @Override
    public void warn(String msg) {

    }
}

class BankAccount {
    private Log log;

    private int balance;

    public BankAccount(Log log) {
        this.log = log;
    }

    public void deposit(int amount) {
        balance += amount;
        log.info("deposited " + amount);
    }
}

public class NullObjectDemo {
    @SuppressWarnings("unchecked")
    public static <T> T noOp(Class<T> itf) {
        return (T) Proxy.newProxyInstance(
                itf.getClassLoader(), new Class<?>[]{itf},
                (((proxy, method, args) -> {
                    if (method.getReturnType().equals(Void.TYPE)) {
                        return null;
                    } else {
                        return method.getReturnType().getConstructor().newInstance();
                    }
                }))
        );
    }

    public static void main(String[] args) {
        ConsoleLog log = new ConsoleLog();
        BankAccount bankAccount = new BankAccount(log);
        bankAccount.deposit(100);

        //instead of giving null
        //  Log nullLog = new NullLog();
        Log nullLog = noOp(Log.class);
        BankAccount account = new BankAccount(nullLog);
        account.deposit(100);
    }
}
