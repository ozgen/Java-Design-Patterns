package chainofresponsibility;

import chainofresponsibility.Query.Argument;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

class Event<Args> {
    private int index = 0;
    private Map<Integer, Consumer<Args>> handlers = new HashMap<>();

    public int subscribe(Consumer<Args> handler) {
        int i = index;
        handlers.put(index++, handler);
        return i;
    }

    public void unsubscribe(int key) {
        handlers.remove(key);
    }

    public void fire(Args args) {
        for (Consumer<Args> handler : handlers.values()) {
            handler.accept(args);
        }
    }
}

class Query {
    public String creatureName;

    enum Argument {
        ATTACK, DEFENSE
    }

    public Argument argument;
    public int result;

    public Query(String creatureName, Argument argument, int result) {
        this.creatureName = creatureName;
        this.argument = argument;
        this.result = result;
    }
}

class Game {

    public Event<Query> queries = new Event<>();
}

class TCreature {

    private Game game;
    public String name;
    public int baseAttack, baseDefense;

    public TCreature(Game game, String name, int baseAttack, int baseDefense) {
        this.game = game;
        this.name = name;
        this.baseAttack = baseAttack;
        this.baseDefense = baseDefense;
    }

    int getAttack() {
        Query query = new Query(name, Argument.ATTACK, baseAttack);
        game.queries.fire(query);
        return query.result;
    }

    int getDefense() {
        Query query = new Query(name, Argument.DEFENSE, baseDefense);
        game.queries.fire(query);
        return query.result;
    }

    @Override
    public String toString() {
        return "CreatureT{" +
                "name='" + name + '\'' +
                ", attack=" + getAttack() +
                ", defense=" + getDefense() +
                '}';
    }
}

class CreatureTModifier {
    protected Game game;
    protected TCreature creatureT;

    public CreatureTModifier(Game game, TCreature creatureT) {
        this.game = game;
        this.creatureT = creatureT;
    }
}

class DoubleTAttackModifier extends CreatureTModifier implements AutoCloseable {

    private int token;

    public DoubleTAttackModifier(Game game, TCreature creatureT) {
        super(game, creatureT);

        token = game.queries.subscribe(q -> {
            if (q.creatureName.equals(creatureT.name) && q.argument == Argument.ATTACK) {
                q.result *= 2;
            }
        });
    }

    @Override
    public void close() {
        game.queries.unsubscribe(token);
    }
}

class IncreaseTDefenseModifier extends CreatureTModifier implements AutoCloseable {
    private int token;

    public IncreaseTDefenseModifier(Game game, TCreature creatureT) {
        super(game, creatureT);
        token = game.queries.subscribe(q -> {
            if (q.creatureName.equals(creatureT.name) && q.argument == Argument.DEFENSE) {
                q.result += 3;
            }
        });
    }

    @Override
    public void close() {
        game.queries.unsubscribe(token);

    }
}

public class CommandQuerySeparationDemo {

    public static void main(String[] args) {
        Game game = new Game();
        TCreature golem = new TCreature(game, "Golem", 3, 3);
        System.out.println(golem);

        IncreaseTDefenseModifier icm = new IncreaseTDefenseModifier(game, golem);
        DoubleTAttackModifier dam = new DoubleTAttackModifier(game, golem);
        try (dam) {
            System.out.println(golem);
        }
        System.out.println(golem);
    }
}
