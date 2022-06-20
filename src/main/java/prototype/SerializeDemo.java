package prototype;

import org.apache.commons.lang3.SerializationUtils;

import java.io.Serializable;

class Foo implements Serializable {
    public String a, b;

    public Foo(String a, String b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public String toString() {
        return "Foo{" +
                "a='" + a + '\'' +
                ", b='" + b + '\'' +
                '}';
    }
}

public class SerializeDemo {

    public static void main(String[] args) {
        Foo foo = new Foo("a", "b");
        Foo foo1 = SerializationUtils.roundtrip(foo);
        foo1.b = "xyz";
        System.out.println(foo);
        System.out.println(foo1);
    }
}
