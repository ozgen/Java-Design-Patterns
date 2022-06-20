package solid.lsp;

//LSP -> Liskov Substitution Principle


public class LSP {

    static void useIt(Rectangle r) {
        int w = r.getWidth();
        r.setHeight(10);
        //area = w * 10

        System.out.println("Expected area of "
                + (w * 10)
                + ", got "+ r.getArea());
    }

    public static void main(String[] args) {

        Rectangle rc = new Rectangle(2, 3);
        useIt(rc);
        Rectangle sq = new Square();
        sq.setWidth(5);
        useIt(sq);
    }
}
