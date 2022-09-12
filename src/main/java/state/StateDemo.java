package state;

class State {
    void on(LightSwitch ls) {
        System.out.println("light is already on.");

    }

    void off(LightSwitch ls) {
        System.out.println("light is already off.");
    }
}

class OnState extends State {
    public OnState() {
        System.out.println("Light turned on");
    }

    @Override
    void off(LightSwitch ls) {
        System.out.println("switching light off...");
        ls.setState(new OffState());
    }
}

class OffState extends State {
    public OffState() {
        System.out.println("light turned off...");
    }

    @Override
    void on(LightSwitch ls) {
        System.out.println("switching light on...");
        ls.setState(new OnState());
    }
}

class LightSwitch {

    private State state; //OnState, OffState

    public LightSwitch() {
        this.state = new OffState();
    }

    void on() {
        state.on(this);
    }

    void off() {
        state.off(this);
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }
}

public class StateDemo {


    public static void main(String[] args) {
        LightSwitch ls = new LightSwitch();
        ls.on();
        ls.off();
        ls.off();
    }
}
