package strategy;

import java.util.List;
import java.util.function.Supplier;

enum StaticOutputFormat {
    MARKDOWN, HTML
}

interface ListStaticStrategy {
    default void start(StringBuilder sb) {
    }

    void addListItem(StringBuilder sb, String item);

    default void end(StringBuilder sb) {
    }
}

class MarkDownListStaticStrategy implements ListStaticStrategy {

    @Override
    public void addListItem(StringBuilder sb, String item) {
        sb.append(" * ").append(item)
                .append(System.lineSeparator());
    }
}

class HtmlListStaticStrategy implements ListStaticStrategy {
    @Override
    public void start(StringBuilder sb) {
        sb.append("<ul>").append(System.lineSeparator());
    }

    @Override
    public void addListItem(StringBuilder sb, String item) {
        sb.append("  <li>")
                .append(item)
                .append("</li>")
                .append(System.lineSeparator());
    }

    @Override
    public void end(StringBuilder sb) {
        sb.append("</ul>").append(System.lineSeparator());
    }
}

class StaticTextProcessor<LS extends ListStaticStrategy> {
    private StringBuilder sb = new StringBuilder();
    private LS listStrategy;

    public StaticTextProcessor(Supplier<? extends LS> constructor) {
        listStrategy = constructor.get();
    }


    public void appendList(List<String> items) {
        listStrategy.start(sb);
        items.forEach(item -> listStrategy.addListItem(sb, item));
        listStrategy.end(sb);
    }

    public void clear() {
        sb.setLength(0);
    }

    @Override
    public String toString() {
        return sb.toString();
    }
}

public class StaticStrategyDemo {

    public static void main(String[] args) {
        StaticTextProcessor<MarkDownListStaticStrategy> tp = new StaticTextProcessor<>(MarkDownListStaticStrategy::new);
        List<String> items = List.of("alpha", "beta", "gamma");
        tp.appendList(items);
        System.out.println(tp);
        StaticTextProcessor<HtmlListStaticStrategy> tp2 = new StaticTextProcessor<>(HtmlListStaticStrategy::new);
        tp2.appendList(items);
        System.out.println(tp2);
        ;
    }
}
