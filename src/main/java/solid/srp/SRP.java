package solid.srp;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

//SRP Single Responsibility Principle
class Journal {

    private final List<String> entities = new ArrayList<>();
    private static int count = 0;

    public void addEntity(String text) {
        entities.add("" + (++count) + ": " + text);
    }

    public void removeEntity(int index) {
        entities.remove(index);
    }

    @Override
    public String toString() {
        return String.join(System.lineSeparator(), entities);
    }

    /**
     * This break the SRP principle
     */
   /* public void save(String filename) throws FileNotFoundException {
        try (PrintStream out = new PrintStream(filename)) {
            out.println(toString());
        }
    }

    public void load(String filename) {
    }

    public void load(URL url) {
    }*/

}

class Persistence {

    public void saveToFile(Journal journal, String filename, boolean overwrite) throws FileNotFoundException {

        if (overwrite || new File(filename).exists()) {
            try (PrintStream out = new PrintStream(filename)) {
                out.println(journal);
            }
        }
    }
}

public class SRP {

    public static void main(String[] args) throws IOException {
        Journal journal = new Journal();
        journal.addEntity("i will go shopping today");
        journal.addEntity("i ate bug");
        System.out.println(journal);
        Persistence p = new Persistence();
        String filename = "/Users/ozgen/Desktop/DesignPatterns/src/solid/srp/journal.txt";
        p.saveToFile(journal, filename, true);

        Runtime.getRuntime().exec("/System/Applications/TextEdit.app/Contents/MacOS/TextEdit "+filename);
    }
}
