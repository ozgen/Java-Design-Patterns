package factories;

/**
 * Kullanici: ozgen
 * Tarih: 10.10.2021
 * Saat: 16:29
 * Aciklama :
 */

class Point2 {
    private double x, y;

    public Point2(double x, double y) {
        this.x = x;
        this.y = y;
    }
}

class Point2Factory {
    public static Point2 newCartesianPoint2(double x, double y) {
        return new Point2(x, y);
    }

    public static Point2 newPolarPoint2(double rho, double theta) {
        return new Point2(rho * Math.cos(theta), rho * Math.sin(theta));
    }
}

public class DemoFactory {

    public static void main(String[] args) {
        Point2 point2 = Point2Factory.newCartesianPoint2(2, 4);
        Point2 point21 = new Point2(3, 5);

    }
}
