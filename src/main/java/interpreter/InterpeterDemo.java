package interpreter;

import interpreter.Token.Type;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class Token {

    public enum Type {
        INTEGER,
        PLUS,
        MINUS,
        LPAREN,
        RPAREN
    }

    public Type type;
    public String text;

    public Token(Type type, String text) {
        this.type = type;
        this.text = text;
    }

    @Override
    public String toString() {
        return "`" + text + "`";
    }
}

public class InterpeterDemo {

    static List<Token> lex(String input) {
        ArrayList<Token> tokens = new ArrayList<>();
        for (int i = 0; i < input.length(); i++) {
            switch (input.charAt(i)) {
                case '+':
                    tokens.add(new Token(Type.PLUS, "+"));
                    break;
                case '-':
                    tokens.add(new Token(Type.MINUS, "-"));
                    break;
                case '(':
                    tokens.add(new Token(Type.LPAREN, "("));
                    break;
                case ')':
                    tokens.add(new Token(Type.RPAREN, ")"));
                    break;
                default:
                    StringBuilder sb = new StringBuilder("" + input.charAt(i));
                    for (int j = i + 1; j < input.length(); j++) {
                        if (Character.isDigit(input.charAt(j))) {
                            sb.append(input.charAt(j));
                            i++;
                        } else {
                            tokens.add(new Token(Type.INTEGER, sb.toString()));
                            break;
                        }
                    }
                    break;
            }
        }
        return tokens;
    }

    public static void main(String[] args) {
        String input = "(13+4)-(12+1)";
        List<Token> tokens = lex(input);
        System.out.println(tokens.stream().map(Token::toString)
                .collect(Collectors.joining("\t")));
    }
}
