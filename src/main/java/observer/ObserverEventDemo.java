package observer;


import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

class Event<Targs> {

    private int count = 0;
    private Map<Integer, Consumer<Targs>> handlers = new HashMap<>();

    public Subscription addHandler(Consumer<Targs> handler) {
        int i = count;
        handlers.put(count++, handler);
        return new Subscription(this, i);
    }

    public void fire(Targs args) {

        for (Consumer<Targs> handler : handlers.values()) {

            handler.accept(args);
        }

    }

    public class Subscription implements AutoCloseable {

        private Event<Targs> event;
        private int id;

        public Subscription(Event<Targs> event, int id) {
            this.event = event;
            this.id = id;
        }

        @Override
        public void close() /*throws Exception */ {
            event.handlers.remove(id);
        }
    }
}

class PropertyChangedEventArgs2 {

    public Object source;
    public String propertyName;

    public PropertyChangedEventArgs2(Object source, String propertyName) {
        this.source = source;
        this.propertyName = propertyName;
    }
}

class Person2 {
    public Event<PropertyChangedEventArgs2> propertyChanged = new Event<>();

    private int age;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if (this.age == age) {
            return;
        }
        this.age = age;
        propertyChanged.fire(new PropertyChangedEventArgs2(this, "age"));
    }
}

public class ObserverEventDemo {

    public static void main(String[] args) {
        Person2 person = new Person2();
        Event<PropertyChangedEventArgs2>.Subscription subs = person.propertyChanged.addHandler(x -> {
            System.out.println(" Person's " + x.propertyName + " has changed.");
        });

        person.setAge(20);
        person.setAge(21);
        subs.close();
        person.setAge(29);
    }
}
