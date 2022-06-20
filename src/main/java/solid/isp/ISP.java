package solid.isp;

//ISP -> Interface Segregation Principle
class Document {

}

interface Machine {

    void print(Document d);

    void fax(Document d) throws Exception;

    void scan(Document d);
}

class MultifunctionalPrinter implements Machine {

    @Override
    public void print(Document d) {

    }

    @Override
    public void fax(Document d) {

    }

    @Override
    public void scan(Document d) {

    }
}

/**
 * This violate ISP because we should segregate the interface
 */
class OldFasionPrinter implements Machine {

    @Override
    public void print(Document d) {

    }

    @Override
    public void fax(Document d) throws Exception {
        throw new Exception();
    }

    @Override
    public void scan(Document d) {

    }
}

interface Printer {
    void print(Document d);
}

interface Scanner {
    void scan(Document d);
}

/**
 * YAGNI -> you ain't going to needed
 */
class JustAPrinter implements Printer {
    @Override
    public void print(Document d) {

    }
}

class Photocopier implements Printer, Scanner {

    @Override
    public void print(Document d) {

    }

    @Override
    public void scan(Document d) {

    }
}

interface MultifunctionDevice extends Printer, Scanner {
}

class MultifunctionMachine implements MultifunctionDevice {

    private Printer printer;
    private Scanner scanner;

    public MultifunctionMachine(Printer printer, Scanner scanner) {
        this.printer = printer;
        this.scanner = scanner;
    }

    @Override
    public void print(Document d) {
        printer.print(d);
    }

    @Override
    public void scan(Document d) {
        scanner.scan(d);
    }
}

public class ISP {
}
