package composite;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class GraphicObject {
    protected String name = "Group";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public GraphicObject() {
    }

    public String color;
    public List<GraphicObject> children = new ArrayList<>();

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        print(sb, 0);
        return sb.toString();
    }

    private void print(StringBuilder sb, int debth) {
        sb.append(String.join("", Collections.nCopies(debth, "*")))
                .append(debth > 0 ? " " : "")
                .append((color == null || color.isEmpty()) ? "" : color + " ")
                .append(getName())
                .append(System.lineSeparator());
        for (GraphicObject child : children) {
            child.print(sb, debth + 1);
        }
    }
}

class Circle extends GraphicObject {
    public Circle(String color) {
        name = "circle";
        this.color = color;
    }
}
class Square extends GraphicObject {
    public Square(String color) {
        name = "square";
        this.color = color;
    }
}

public class CompositeDemo {

    public static void main(String[] args) {
        GraphicObject drawing = new GraphicObject();
        drawing.setName("My Drawing");
        drawing.children.add(new Square("red"));
        drawing.children.add(new Circle("yellow"));
        GraphicObject group = new GraphicObject();
        group.children.add(new Square("blue"));
        group.children.add(new Circle("blue"));
        drawing.children.add(group);
        System.out.println(drawing);
    }
}
