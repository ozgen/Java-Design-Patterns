package chainofresponsibility;

class Creature {
    public String name;
    public int attack, defence;

    public Creature(String name, int attack, int defence) {
        this.name = name;
        this.attack = attack;
        this.defence = defence;
    }

    @Override
    public String toString() {
        return "Creature{" +
                "name='" + name + '\'' +
                ", attack=" + attack +
                ", defence=" + defence +
                '}';
    }
}

class CreatureModifier {
    protected Creature creature;
    protected CreatureModifier next;

    public CreatureModifier(Creature creature) {
        this.creature = creature;
    }

    public void add(CreatureModifier cm) {
        if (next != null) {
            next.add(cm);
        } else {
            next = cm;
        }
    }

    public void handle() {
        if (next != null) {
            next.handle();
        }
    }
}

class IncreaseDefenceModifier extends CreatureModifier {

    public IncreaseDefenceModifier(Creature creature) {
        super(creature);
    }

    @Override
    public void handle() {
        System.out.println("Increasing " + creature.name + "'s defence");
        creature.defence += 3;
        super.handle();
    }
}

class DoubleAttackModifier extends CreatureModifier {

    public DoubleAttackModifier(Creature creature) {
        super(creature);
    }

    @Override
    public void handle() {
        System.out.println("Doubling " + creature.name + "'s attack");
        creature.attack *= 2;
        super.handle();
    }
}

class NoBonusesModifier extends CreatureModifier {
    public NoBonusesModifier(Creature creature) {
        super(creature);
    }

    @Override
    public void handle() {
        System.out.println("no bonuses for you!");
    }
}

public class MethodChainDemo {

    public static void main(String[] args) {
        Creature goblin = new Creature("Goblin", 2, 2);
        System.out.println(goblin);

        CreatureModifier root = new CreatureModifier(goblin);

//        root.add(new NoBonusesModifier(goblin));

        System.out.println("Lets increase defence of goblin");
        IncreaseDefenceModifier increaseDefenceModifier = new IncreaseDefenceModifier(goblin);
        root.add(increaseDefenceModifier);
        System.out.println("Lets double attack of goblin");
        DoubleAttackModifier doubleAttackModifier = new DoubleAttackModifier(goblin);
        root.add(doubleAttackModifier);


        root.handle();

        System.out.println(goblin);
    }
}
