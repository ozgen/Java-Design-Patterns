package flyweight;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

class Sentence {
    private String[] words;
    private Map<Integer, WordToken> tokens = new HashMap<>();

    public Sentence(String plainText) {
        words = plainText.split(" ");
    }

    public WordToken getWord(int index) {
        WordToken wt = new WordToken();
        tokens.put(index, wt);
        return tokens.get(index);
    }

    @Override
    public String toString() {
        ArrayList<String> ws = new ArrayList<>();
        for (int i = 0; i < words.length; ++i) {
            String w = words[i];
            if (tokens.containsKey(i) && tokens.get(i).capitalize)
                w = w.toUpperCase();
            ws.add(w);
        }
        return String.join(" ", ws);
    }

    class WordToken {
        public boolean capitalize;
    }
}

public class Demo {


    public static void main(String[] args) {
        Sentence sentence = new Sentence("This is a brave world");
        sentence.getWord(3).capitalize = true;
        System.out.println(sentence);
    }
}