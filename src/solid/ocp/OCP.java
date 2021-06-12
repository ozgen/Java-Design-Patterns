package solid.ocp;

// OCP Open-Close Principle
/**
 * OCP -> Open for extention Close for modification
 */

import java.util.List;
import java.util.stream.Stream;

enum Color {
    RED, GREEN, BLUE
}

enum Size {
    SMALL, MEDIUM, LARGE, HUGE
}

class Product {
    public String name;
    public Color color;
    public Size size;

    public Product(String name, Color color, Size size) {
        this.name = name;
        this.color = color;
        this.size = size;
    }
}

class ProductFilter {

    public Stream<Product> filterByColor(List<Product> products, Color color) {
        return products.stream().filter(p -> p.color == color);
    }

    public Stream<Product> filterBySize(List<Product> products, Size size) {
        return products.stream().filter(p -> p.size == size);
    }

    public Stream<Product> filterByColorAndSize(List<Product> products, Color color, Size size) {
        return products.stream().filter(p -> p.color == color && p.size == size);
    }
}

/**
 * With the specification of OCP Principle
 * <p>
 * New Version
 */

interface Specification<T> {

    boolean isSatisfied(T item);
}

interface Filter<T> {

    Stream<T> filter(List<T> items, Specification<T> spec);
}

class ColorSpecification implements Specification<Product> {

    private Color color;

    public ColorSpecification(Color color) {
        this.color = color;
    }

    @Override
    public boolean isSatisfied(Product item) {
        return item.color == color;
    }
}

class SizeSpecification implements Specification<Product> {

    private Size size;

    public SizeSpecification(Size size) {
        this.size = size;
    }

    @Override
    public boolean isSatisfied(Product item) {
        return item.size == size;
    }
}

class AndSpecification<T> implements Specification<T> {

    private Specification<T> spec1, spec2;

    public AndSpecification(Specification<T> spec1, Specification<T> spec2) {
        this.spec1 = spec1;
        this.spec2 = spec2;
    }

    @Override
    public boolean isSatisfied(T item) {
        return spec1.isSatisfied(item) && spec2.isSatisfied(item);
    }
}

class BetterFilter implements Filter<Product> {

    @Override
    public Stream<Product> filter(List<Product> items, Specification<Product> spec) {
        return items.stream().filter(p -> spec.isSatisfied(p));
    }
}


public class OCP {

    public static void main(String[] args) {
        Product apple = new Product("Apple", Color.GREEN, Size.SMALL);
        Product tree = new Product("Tree", Color.GREEN, Size.LARGE);
        Product house = new Product("House", Color.BLUE, Size.LARGE);

        List<Product> products = List.of(apple, tree, house);

        ProductFilter pf = new ProductFilter();
        System.out.println("Green products (old): ");
        pf.filterByColor(products, Color.GREEN)
                .forEach(p -> System.out.println(" - " + p.name + " is Green"));

        BetterFilter bf = new BetterFilter();
        System.out.println("Green products (new): ");
        bf.filter(products, new ColorSpecification(Color.GREEN))
                .forEach(p -> System.out.println(" - " + p.name + " is Green"));

        System.out.println("LARGE, BLUE items: ");

        bf.filter(products, new AndSpecification<Product>(new ColorSpecification(Color.BLUE),
                new SizeSpecification(Size.LARGE)))
                .forEach(product -> System.out.println(" - " + product.name + " is large and blue"));


    }

}
