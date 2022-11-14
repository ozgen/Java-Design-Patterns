package visitor;


interface ExpressionVisitor {

    void visit(CDoubleExpression de);

    void visit(CAdditionExpression ae);
}

abstract class CExpression {

    public abstract void accept(ExpressionVisitor visitor);
}

class CDoubleExpression extends CExpression {
    public double value;

    public CDoubleExpression(double value) {
        this.value = value;
    }

    @Override
    public void accept(ExpressionVisitor visitor) {
        visitor.visit(this);
    }
}

class CAdditionExpression extends CExpression {
    public CExpression left, right;

    public CAdditionExpression(CExpression left, CExpression right) {
        this.left = left;
        this.right = right;
    }


    @Override
    public void accept(ExpressionVisitor visitor) {
        visitor.visit(this);
    }
}

class EPrinter implements ExpressionVisitor {
    private StringBuilder sb = new StringBuilder();

    @Override
    public void visit(CDoubleExpression de) {
        sb.append(de.value);
    }

    @Override
    public void visit(CAdditionExpression ae) {
        sb.append("(");
        ae.left.accept(this);
        sb.append("+");
        ae.right.accept(this);
        sb.append(")");
    }

    @Override
    public String toString() {
        return sb.toString();
    }
}

class ExpressionCalculator implements ExpressionVisitor {

    public double result;

    @Override
    public void visit(CDoubleExpression de) {
        result = de.value;
    }

    @Override
    public void visit(CAdditionExpression ae) {
        ae.left.accept(this);
        double a = result;
        ae.right.accept(this);
        double b = result;
        result = a + b;
    }
}

public class ClassicVisitorDemo {

    public static void main(String[] args) {
        // 1 + (2 +3)
        CAdditionExpression e = new CAdditionExpression(new CDoubleExpression(1d),
                new CAdditionExpression(
                        new CDoubleExpression(2d), new CDoubleExpression(3d)));

        EPrinter ep = new EPrinter();
        ep.visit(e);
        System.out.println(ep);

        ExpressionCalculator ec = new ExpressionCalculator();
        ec.visit(e);
        System.out.println(ep + " = " + ec.result);

    }
}
