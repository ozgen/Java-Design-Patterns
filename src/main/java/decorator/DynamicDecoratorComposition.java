package decorator;

import java.util.function.Supplier;

interface Shape {
    String info();
}

class Circle implements Shape {

    private float radius;

    public Circle() {
    }

    public Circle(float radius) {
        this.radius = radius;
    }

    void resize(float factor) {
        radius *= factor;
    }

    @Override
    public String info() {
        return "A circle of radius = " + radius;
    }
}

class Square implements Shape {

    private float side;

    public Square() {
    }

    public Square(float side) {
        this.side = side;
    }

    @Override
    public String info() {
        return "A square of side = " + side;
    }
}

class ColoredShapeOld implements Shape {

    private Shape shape;
    private String color;

    public ColoredShapeOld(Shape shape, String color) {
        this.shape = shape;
        this.color = color;
    }

    @Override
    public String info() {
        return shape.info() + " has the color " + color;
    }
}

class TransparentShapeOld implements Shape {

    private Shape shape;
    private int transparency;

    public TransparentShapeOld(Shape shape, int transparency) {
        this.shape = shape;
        this.transparency = transparency;
    }

    @Override
    public String info() {
        return shape.info() + " has " + transparency + " % transparency";
    }
}

class ColoredShape<T extends Shape> implements Shape {

    private Shape shape;
    private String color;


    public ColoredShape(Supplier<? extends T> ctor, String color) {
        this.shape = ctor.get();
        this.color = color;
    }

    @Override
    public String info() {
        return shape.info() + " has the color " + color;
    }
}

class TransparentShape<T extends Shape> implements Shape {

    private Shape shape;
    private int transparency;


    public TransparentShape(Supplier<? extends T> ctor, int transparency) {
        this.shape = ctor.get();
        this.transparency = transparency;
    }

    @Override
    public String info() {
        return shape.info() + " has " + transparency + " % transparency";

    }
}

public class DynamicDecoratorComposition {

    public static void main(String[] args) {
        Circle circle = new Circle(10);
        System.out.println(circle.info());
        ColoredShapeOld coloredShape = new ColoredShapeOld(new Square(20), "blue");
        System.out.println(coloredShape.info());
        TransparentShapeOld transparentShape = new TransparentShapeOld(new ColoredShapeOld(new Circle(5), "red"), 15);
        System.out.println(transparentShape.info());

        ColoredShape<Square> blueSquare = new ColoredShape<>(() -> new Square(20), "blue");
        System.out.println(blueSquare.info());

        TransparentShape<ColoredShape<Circle>> coloredShapeTransparentShape = new TransparentShape<>(
                ()-> new ColoredShape<>(()->new Circle(15), "red"), 50
        );
        System.out.println(coloredShapeTransparentShape.info());
    }
}
