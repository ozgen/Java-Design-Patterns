package visitor;

abstract class RExpression {
}

class RDoubleExpression extends RExpression {
    public double value;

    public RDoubleExpression(double value) {
        this.value = value;
    }


}

class RAdditionExpression extends RExpression {
    public RExpression left, right;

    public RAdditionExpression(RExpression left, RExpression right) {
        this.left = left;
        this.right = right;
    }


}

class ExpressionPrinter {
    public static void print(RExpression e, StringBuilder sb) {
        if (e.getClass() == RDoubleExpression.class) {
            sb.append(((RDoubleExpression) e).value);
        } else if (e.getClass() == RAdditionExpression.class) {
            RAdditionExpression ae = (RAdditionExpression) e;
            sb.append("(");
            print(ae.left, sb);
            sb.append("+");
            print(ae.right, sb);
            sb.append(")");
        }
    }
}

public class ReflectiveVisitorDemo {

    public static void main(String[] args) {
        // 1 + (2 +3)
        RAdditionExpression e = new RAdditionExpression(new RDoubleExpression(1d),
                new RAdditionExpression(
                        new RDoubleExpression(2d), new RDoubleExpression(3d)));
        StringBuilder sb = new StringBuilder();

        ExpressionPrinter.print(e, sb);
        System.out.println(sb);


    }
}
