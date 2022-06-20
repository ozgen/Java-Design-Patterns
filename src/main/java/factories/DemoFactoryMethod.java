package factories;

/**
 * Kullanici: ozgen
 * Tarih: 10.10.2021
 * Saat: 16:12
 * Aciklama :
 */

class Point {
    private double x, y;

    private Point(double x, double y) {
        this.x = x;
        this.y = y;
    }
//
//    public Point(double rho, double theta) {
//        x = rho * Math.cos(theta);
//        y = rho * Math.sin(theta);
//    }

    public static Point newCartesianPoint(double x, double y) {
        return new Point(x, y);
    }

    public static Point newPolarPoint(double rho, double theta) {
        return new Point(rho * Math.cos(theta), rho * Math.sin(theta));
    }
}

public class DemoFactoryMethod {

    public static void main(String[] args) {
        Point point = Point.newPolarPoint(2, 3);
    }
}
