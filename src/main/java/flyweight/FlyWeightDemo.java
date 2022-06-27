package flyweight;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

class User {
    String fullName;

    public User(String fullName) {
        this.fullName = fullName;
    }
}

class User2 {
    static List<String> strings = new ArrayList<>();
    private int[] names;

    public User2(String fullName) {

        Function<String, Integer> getOrAdd = (String s) -> {
            int idx = strings.indexOf(s);
            if (idx != -1) {
                return idx;
            } else {
                strings.add(s);
                return strings.size()-1;
            }
        };

        names = Arrays.stream(fullName.split(" "))
                .mapToInt(getOrAdd::apply)
                .toArray();
    }
}

public class FlyWeightDemo {

    public static void main(String[] args) {
//        User user = new User("Adam Smith");
//        User user2 = new User("John Smith");
        User2 user = new User2("Adam Smith");
        User2 user2 = new User2("John Smith");
        System.out.println(User2.strings);
    }
}
