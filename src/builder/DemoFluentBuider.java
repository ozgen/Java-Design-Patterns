package builder;

/**
 * Kullanici: ozgen
 * Tarih: 10.10.2021
 * Saat: 15:06
 * Aciklama :
 */

class Person {
    public String name;

    public String position;

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", position='" + position + '\'' +
                '}';
    }
}

class PersonBuilder<SELF extends PersonBuilder<SELF>> {
    protected Person person = new Person();

    public SELF withName(String name) {
        person.name = name;
        return self();
    }

    public Person build() {
        return person;
    }

    protected SELF self() {
        return (SELF) this;
    }
}

class EmployeeBuilder extends PersonBuilder<EmployeeBuilder> {
    public EmployeeBuilder worksAt(String position) {
        person.position = position;
        return self();
    }

    @Override
    protected EmployeeBuilder self() {
        return this;
    }
}

public class DemoFluentBuider {

    public static void main(String[] args) {

        PersonBuilder pb = new PersonBuilder();

        Person ozgen = pb.withName("ozgen").build();

        EmployeeBuilder eb = new EmployeeBuilder();
        Person ozgen2 = eb.withName("ozgen")
                .worksAt("Developer")
                .build();
        System.out.println(ozgen2);

    }
}
