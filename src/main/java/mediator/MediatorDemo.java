package mediator;


import java.util.ArrayList;
import java.util.List;

class Person {
    public String name;
    private List<String> chatLog = new ArrayList<>();
    public ChatRoom room;

    public Person(String name) {
        this.name = name;
    }

    public void receive(String sender, String message) {
        String s = sender + ": '" + message + "'";
        System.out.println("[" + name + "'s chat session] " + s);
        chatLog.add(s);
    }

    public void say(String message) {
        room.broadcast(name, message);
    }

    public void privateMessage(String who, String message) {
        room.message(name, who, message);
    }
}

class ChatRoom {

    private List<Person> people = new ArrayList<>();


    public void join(Person p) {
        String jMess = p.name + " joins the room.";
        broadcast("room", jMess);
        p.room = this;
        people.add(p);
    }

    public void broadcast(String name, String message) {

        for (Person person : people) {
            if (!person.name.equals(name)) {
                person.receive(name, message);
            }
        }
    }

    public void message(String name, String who, String message) {

        people.stream().filter(p -> p.name.equals(who))
                .findFirst()
                .ifPresent(p -> p.receive(name, message));
    }
}

public class MediatorDemo {

    public static void main(String[] args) {
        ChatRoom room = new ChatRoom();
        Person john = new Person("John");
        Person jane = new Person("Jane");

        room.join(john);
        room.join(jane);

        john.say("Hey room!");
        jane.say("Hey john :)");

        Person simon = new Person("Simon");
        room.join(simon);
        simon.say("hi everyone!!");

        jane.privateMessage(simon.name, "glad you could you join us?");

    }
}
