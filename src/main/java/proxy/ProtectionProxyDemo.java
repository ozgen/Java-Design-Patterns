package proxy;

interface Drivable {
    void drive();
}

class Driver {
    public int age;

    public Driver(int age) {
        this.age = age;
    }
}

class Car implements Drivable {

    protected Driver driver;

    public Car(Driver driver) {
        this.driver = driver;
    }

    @Override
    public void drive() {
        System.out.println("car being driven");
    }
}

class CarProxy extends Car {

    public CarProxy(Driver driver) {
        super(driver);
    }

    @Override
    public void drive() {
        if (driver.age >= 18) {
            super.drive();
        } else {
            System.out.println("Driver too young to drive!");
        }
    }
}

public class ProtectionProxyDemo {

    public static void main(String[] args) {
        Car car = new Car(new Driver(12));
        car.drive();
        Car carProxy = new CarProxy(new Driver(12));
        carProxy.drive();

    }
}
