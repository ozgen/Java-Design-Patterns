package flyweight;

import java.util.ArrayList;
import java.util.List;

class FormattedText {

    private String plainText;
    private boolean[] capitalize;

    public FormattedText(String plainText) {
        this.plainText = plainText;
        capitalize = new boolean[plainText.length()];
    }

    public void capitalize(int start, int end) {
        for (int i = start; i < end; i++) {
            capitalize[i] = true;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < plainText.length(); i++) {
            char c = plainText.charAt(i);
            sb.append(capitalize[i] ? Character.toUpperCase(c) : c);
        }
        return sb.toString();
    }
}

class BetterFormattedText {

    private String plainText;
    private List<TextRange> formatting = new ArrayList<>();

    public BetterFormattedText(String plainText) {
        this.plainText = plainText;
    }

    public TextRange getRange(int start, int end) {
        TextRange range = new TextRange(start, end);
        formatting.add(range);
        return range;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < plainText.length(); i++) {
            char c = plainText.charAt(i);
            for (TextRange range : formatting) {
                if (range.capitalize && range.covers(i)) {
                    c = Character.toUpperCase(c);
                }
            }
            sb.append(c);
        }
        return sb.toString();
    }

    public class TextRange {
        public int start, end;
        public boolean capitalize, bold, italic;

        public TextRange(int start, int end) {
            this.start = start;
            this.end = end;
        }

        public boolean covers(int position) {
            return position >= start && position <= end;
        }
    }
}

public class FlyWeightDemoForFormattedText {

    public static void main(String[] args) {

        FormattedText formattedText = new FormattedText("This is the most powerful text app.");
        formattedText.capitalize(10, 15);
        System.out.println(formattedText);

        BetterFormattedText betterFormattedText = new BetterFormattedText("Here is a better formatted text");
        betterFormattedText.getRange(10, 15).capitalize = true;
        System.out.println(betterFormattedText);
    }
}
