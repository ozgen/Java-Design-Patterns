package visitor;


// really creepy implementation of acyclic visitor

interface Visitor {
}

interface AExpressionVisitor extends Visitor {
    void visit(AExpression obj);
}

interface ADoubleExpressionVisitor extends Visitor {
    void visit(ADoubleExpression obj);
}

interface AAdditionExpressionVisitor extends Visitor {
    void visit(AAdditionExpression obj);


}

abstract class AExpression {
    // optional
    public void accept(Visitor visitor) {
        if (visitor instanceof AExpressionVisitor) {
            ((AExpressionVisitor) visitor).visit(this);
        }
    }
}

class ADoubleExpression extends AExpression {
    public double value;

    public ADoubleExpression(double value) {
        this.value = value;
    }

    @Override
    public void accept(Visitor visitor) {
        if (visitor instanceof ADoubleExpressionVisitor) {
            ((ADoubleExpressionVisitor) visitor).visit(this);
        }
    }
}

class AAdditionExpression extends AExpression {
    public AExpression left, right;

    public AAdditionExpression(AExpression left, AExpression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public void accept(Visitor visitor) {
        if (visitor instanceof AAdditionExpressionVisitor) {
            ((AAdditionExpressionVisitor) visitor).visit(this);
        }
    }
}

class ExpPrinter implements
        ADoubleExpressionVisitor,
        AAdditionExpressionVisitor {
    private StringBuilder sb = new StringBuilder();

    @Override
    public void visit(ADoubleExpression obj) {
        sb.append(obj.value);
    }

    @Override
    public void visit(AAdditionExpression obj) {
        sb.append('(');
        obj.left.accept(this);
        sb.append('+');
        obj.right.accept(this);
        sb.append(')');
    }

    @Override
    public String toString() {
        return sb.toString();
    }
}

class AcyclicVisitorDemo {
    public static void main(String[] args) {
        AAdditionExpression e = new AAdditionExpression(
                new ADoubleExpression(1d),
                new AAdditionExpression(
                        new ADoubleExpression(2d),
                        new ADoubleExpression(3d)
                )
        );
        ExpPrinter ep = new ExpPrinter();
        ep.visit(e);
        System.out.println(ep.toString());
    }
}
