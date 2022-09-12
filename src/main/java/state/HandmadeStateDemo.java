package state;

import org.javatuples.Pair;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

enum StateEnm {
    OFF_HOOK, // starting
    ON_HOOK, // terminal
    CONNECTING,
    CONNECTED,
    ON_HOLD
}

enum Trigger {
    CALL_DIALED,
    HUNG_UP,
    CALL_CONNECTED,
    PLACED_ON_HOLD,
    TAKEN_OFF_HOLD,
    LEFT_MESSAGE,
    STOP_USING_PHONE
}

public class HandmadeStateDemo {

    private static Map<StateEnm, List<Pair<Trigger, StateEnm>>> rules = new HashMap<>();

    static {
        rules.put(StateEnm.OFF_HOOK, List.of(
                new Pair<>(Trigger.CALL_DIALED, StateEnm.CONNECTING),
                new Pair<>(Trigger.STOP_USING_PHONE, StateEnm.ON_HOOK)
        ));
        rules.put(StateEnm.CONNECTING, List.of(
                new Pair<>(Trigger.HUNG_UP, StateEnm.OFF_HOOK),
                new Pair<>(Trigger.CALL_CONNECTED, StateEnm.CONNECTED)
        ));
        rules.put(StateEnm.CONNECTED, List.of(
                new Pair<>(Trigger.LEFT_MESSAGE, StateEnm.OFF_HOOK),
                new Pair<>(Trigger.HUNG_UP, StateEnm.OFF_HOOK),
                new Pair<>(Trigger.PLACED_ON_HOLD, StateEnm.ON_HOLD)
        ));
        rules.put(StateEnm.ON_HOLD, List.of(
                new Pair<>(Trigger.TAKEN_OFF_HOLD, StateEnm.CONNECTED),
                new Pair<>(Trigger.HUNG_UP, StateEnm.OFF_HOOK)
        ));
    }

    private static StateEnm currentState = StateEnm.OFF_HOOK;
    private static StateEnm exitState = StateEnm.ON_HOOK;

    public static void main(String[] args) {
        BufferedReader console = new BufferedReader(
                new InputStreamReader(System.in)
        );

        while (true) {
            System.out.println("The phone is currently " + currentState);
            System.out.println("Select a trigger: ");
            for (int i = 0; i < rules.get(currentState).size(); ++i) {
                Trigger trigger = rules.get(currentState).get(i).getValue0();
                System.out.println(" - " + i + ". " + trigger);
            }

            boolean parseOK;
            int choice = 0;
            do {
                try {
                    System.out.println("Please enter your choice: ");
                    choice = Integer.parseInt(console.readLine());
                    parseOK = choice >= 0 && choice < rules.get(currentState).size();
                } catch (IOException e) {
                    parseOK = false;
                }
            } while (!parseOK);

            currentState = rules.get(currentState).get(choice).getValue1();

            if (currentState == exitState) break;
        }
        System.out.println("we are done!...");
    }
}
