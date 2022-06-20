package prototype;

class Addres {
    public String streetAddr, city, country;

    public Addres(String streetAddr, String city, String country) {
        this.streetAddr = streetAddr;
        this.city = city;
        this.country = country;
    }


    public Addres(Addres other) {
        this(other.streetAddr, other.city, other.country);
    }

    @Override
    public String toString() {
        return "Addres{" +
                "streetAddr='" + streetAddr + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}

class Employee {

    public String name;
    public Addres addres;

    public Employee(String name, Addres addres) {
        this.name = name;
        this.addres = addres;
    }

    public Employee(Employee other) {
        name = other.name;
        addres = new Addres(other.addres);

    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", addres=" + addres +
                '}';
    }
}

public class CopyContructorDemo {

    public static void main(String[] args) {

        Employee john = new Employee("john", new Addres("123 London", "London", "UK"));
        Employee chris = new Employee(john);
        chris.name = "chris";

        System.out.println(chris);
        System.out.println(john);
    }
}
