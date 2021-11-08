package builder;

import java.util.ArrayList;
import java.util.Collections;

class HtmlElement {
    public String name, text;
    public ArrayList<HtmlElement> elements = new ArrayList<>();
    private final int indentSize = 2;
    private final String newLine = System.lineSeparator();

    public HtmlElement() {
    }

    public HtmlElement(String name, String text) {
        this.name = name;
        this.text = text;
    }

    private String toStringImpl(int indent) {
        StringBuilder sb = new StringBuilder();
        String i = String.join("", Collections.nCopies(indent * indentSize, " "));
        sb.append(String.format("%s<%s>%s", i, name, newLine));
        if (text != null && !text.isEmpty()) {
            sb.append(String.join("", Collections.nCopies(indentSize * (indent + 1), " ")))
                    .append(text)
                    .append(newLine);
        }
        for (HtmlElement element : elements)
            sb.append(element.toStringImpl(indent + 1));

        sb.append(String.format("%s</%s>%s", i, name, newLine));

        return sb.toString();
    }

    @Override
    public String toString() {
        return toStringImpl(0);
    }
}

class HtmlBuilder {
    private String rootName;
    private HtmlElement root = new HtmlElement();

    public HtmlBuilder(String rootName) {
        this.rootName = rootName;
        this.root.name = rootName;
    }

    public void addChild(String childName, String childText) {
        HtmlElement e = new HtmlElement(childName, childText);
        root.elements.add(e);
    }

    /**
     * fluent builder that returns own class...
     * @param childName
     * @param childText
     * @return
     */
    public HtmlBuilder addChildFluent(String childName, String childText) {
        HtmlElement e = new HtmlElement(childName, childText);
        root.elements.add(e);
        return this;
    }

    public void clear() {
        root = new HtmlElement();
        root.name = rootName;
    }

    @Override
    public String toString() {
        return root.toString();
    }
}

public class Builder {

    public static void main(String[] args) {
        String hello = "hello";
        System.out.println("<p>" + hello + "</p>");

        //what if we have to iterate an array to manipulate
        String[] words = new String[]{"hello", "world"};

        /**
         * we use StringBuilder to create a new string that we want
         */
        StringBuilder sb = new StringBuilder();

        sb.append("<ul>\n");

        for (String word : words) {
            sb.append(String.format("<li>%s</li>\n", word));
        }

        sb.append("</ul>\n");
        System.out.println(sb);

        /**
         * Html builder example
         */

        HtmlBuilder builder = new HtmlBuilder("ul");
       // builder.addChild("li","hello");
       // builder.addChild("li","world");


        builder.addChildFluent("li","hello");
        builder.addChildFluent("li","world");
        System.out.println(builder);

    }
}
