package visitor;

abstract class Expression {
    abstract void print(StringBuilder sb);
}

class DoubleExpression extends Expression {
    private double value;

    public DoubleExpression(double value) {
        this.value = value;
    }

    @Override
    void print(StringBuilder sb) {
        sb.append(value);
    }
}

class AdditionExpression extends Expression {
    private Expression left, right;

    public AdditionExpression(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    void print(StringBuilder sb) {
        sb.append("(");
        left.print(sb);
        sb.append("+");
        right.print(sb);
        sb.append(")");
    }
}

public class VisitorDemo {

    public static void main(String[] args) {
        // 1 + (2 +3)---- intrusive visitor demo
        AdditionExpression e = new AdditionExpression(new DoubleExpression(1d),
                new AdditionExpression(
                        new DoubleExpression(2d), new DoubleExpression(3d)));
        StringBuilder sb = new StringBuilder();
        e.print(sb);

        System.out.println(sb);

        // -------- end -------


    }
}
