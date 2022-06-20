package solid.dip;

//DIP -> Dependency Inversion Principle

import org.javatuples.Triplet;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * A. High-level modules should not depend on low-level modules
 * Both should depend on abstractions
 * <p>
 * B. Abstractions should not depend on details.
 * Details should depend on abstractions
 */

enum Relationship {
    PARENT,
    CHILD,
    SIBLING
}

class Person {
    public String name;

    public Person(String name) {
        this.name = name;
    }
}

class Relationships { // low-level module

    private List<Triplet<Person, Relationship, Person>> relations = new ArrayList<>();

    public void addParentAndChild(Person parent, Person child) {
        relations.add(new Triplet<>(parent, Relationship.PARENT, child));
        relations.add(new Triplet<>(child, Relationship.CHILD, parent));
    }

    public List<Triplet<Person, Relationship, Person>> getRelations() {
        return relations;
    }
}

class Research { // high-level module
    /**
     * this is violating the DIP
     *
     * @param relationships
     */
  /*  public Research(Relationships relationships) {
        relationships.getRelations().stream().filter(x -> x.getValue0().name.equals("John")
                && x.getValue1() == Relationship.PARENT)
                .forEach(childrenTp -> System.out.println("John has a child called as " + childrenTp.getValue2().name));
    }*/

    public Research(RelationshipBrowser relationshipBrowser) {
        relationshipBrowser.findAllChildrenOf("John").stream()
                .forEach(child -> System.out.println("John has a child called as " + child.name));
    }
}

/**
 * in order to solve this issue
 */

interface RelationshipBrowser {

    List<Person> findAllChildrenOf(String name);
}

class RelationshipsNew implements RelationshipBrowser { // low-level module

    private List<Triplet<Person, Relationship, Person>> relations = new ArrayList<>();

    public void addParentAndChild(Person parent, Person child) {
        relations.add(new Triplet<>(parent, Relationship.PARENT, child));
        relations.add(new Triplet<>(child, Relationship.CHILD, parent));
    }

    public List<Triplet<Person, Relationship, Person>> getRelations() {
        return relations;
    }

    @Override
    public List<Person> findAllChildrenOf(String name) {
        return relations.stream().filter(x -> x.getValue0().name.equals(name)
                && x.getValue1() == Relationship.PARENT).map(Triplet::getValue2)
                .collect(Collectors.toList());
    }
}


public class DIP {

    public static void main(String[] args) {
        Person john = new Person("John");
        Person child1 = new Person("Cris");
        Person child2 = new Person("Anny");
/*
        Relationships relationships = new Relationships();
        relationships.addParentAndChild(john, child1);
        relationships.addParentAndChild(john, child2);*/

        RelationshipsNew relationships = new RelationshipsNew();
        relationships.addParentAndChild(john, child1);
        relationships.addParentAndChild(john, child2);

        new Research(relationships);

    }
}
