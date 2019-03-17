package com.company.Visitor;

public class Main {
    public static void main(String[] args) {
        /*Element body = new BodyElement();
        Element engine = new EngineElement();
        Visitor hooligan = new HooliganVisitor();

        body.accept(hooligan);
        engine.accept(hooligan);*/

        Element car = new CarElement();
        car.accept(new HooliganVisitor());
        System.out.println();
        car.accept(new MechanicVisitor());
    }
}

interface Visitor {
    void visit(EngineElement engine);

    void visit(BodyElement body);

    void visit(CarElement car);

    void visit(WheelElement wheel);
}

interface Element {
    void accept(Visitor visitor);
}

class BodyElement implements Element {

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}

class EngineElement implements Element {

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}

class WheelElement implements Element {
    private String name;

    public WheelElement(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}

class CarElement implements Element {
    Element[] elements;

    public CarElement() {
        this.elements = new Element[]{new WheelElement("Front left wheel"), new WheelElement("Font right wheel"), new WheelElement("Back left wheel"),
                new WheelElement("Back right wheel"), new BodyElement(), new EngineElement()};
    }

    @Override
    public void accept(Visitor visitor) {
        for (Element element : elements)
            element.accept(visitor);
        visitor.visit(this);
    }
}

class HooliganVisitor implements Visitor {

    @Override
    public void visit(EngineElement engine) {
        System.out.println("Started the engine");
    }

    @Override
    public void visit(BodyElement body) {
        System.out.println("Knocked the body");
    }

    @Override
    public void visit(CarElement car) {
        System.out.println("Smoked in the car");
    }

    @Override
    public void visit(WheelElement wheel) {
        System.out.println("Kick " + wheel.getName() + " wheel");
    }
}

class MechanicVisitor implements Visitor {

    @Override
    public void visit(EngineElement engine) {
        System.out.println("Checked the engine");
    }

    @Override
    public void visit(BodyElement body) {
        System.out.println("Repaired the body");
    }

    @Override
    public void visit(CarElement car) {
        System.out.println("Polished body");
    }

    @Override
    public void visit(WheelElement wheel) {
        System.out.println("Repaired " + wheel.getName() + " wheel");
    }
}
