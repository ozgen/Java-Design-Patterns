package proxy;

class Property<T> {
    T value;

    public Property(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        //logging
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Property<?> property = (Property<?>) o;

        return value != null ? value.equals(property.value) : property.value == null;
    }

    @Override
    public int hashCode() {
        return value != null ? value.hashCode() : 0;
    }
}

class Creature {
    Property<Integer> agility = new Property<>(10);

    public int getAgility() {
        return agility.getValue();
    }

    public void setAgility(int agility) {
        this.agility.setValue(agility);
    }

    //    private int agility;
//
//    public Creature() {
//        agility = 123;
//    }
//
//    public int getAgility() {
//        return agility;
//    }
//
//    public void setAgility(int agility) {
//        this.agility = agility;
//    }
}

public class PropertyProxyDemo {

    public static void main(String[] args) {
        Creature creature = new Creature();
        creature.setAgility(12);
    }
}
