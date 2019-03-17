package com.company.Composite;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Shape square1 = new Squuare();
        Shape square2 = new Squuare();
        Shape triangle1 = new Triaangle();

        Shape square3 = new Squuare();
        Shape circle1 = new Cicrle();
        Shape circle2 = new Cicrle();
        Shape circle3 = new Cicrle();

        Composite composite = new Composite();
        Composite composite1 = new Composite();
        Composite composite2 = new Composite();

        composite1.addComponent(square1);
        composite1.addComponent(square2);
        composite1.addComponent(triangle1);

        composite2.addComponent(square3);
        composite2.addComponent(circle1);
        composite2.addComponent(circle2);
        composite2.addComponent(circle3);

        composite.addComponent(composite1);
        composite.addComponent(composite2);
        composite.addComponent(new Triaangle());

        composite.draw();

    }

}

interface Shape {
    void draw();
}

class Squuare implements Shape {
    public void draw() {
        System.out.println("I am square");
    }
}

class Triaangle implements Shape {
    public void draw() {
        System.out.println("I am triangle");
    }
}

class Cicrle implements Shape {
    public void draw() {
        System.out.println("I am circle");
    }
}

class Composite implements Shape {
    private List<Shape> components = new ArrayList<>();

    public void addComponent(Shape component) {
        components.add(component);
    }

    public void removeComponent(Shape component) {
        components.remove(component);
    }

    public void draw() {
        for (Shape component : components) {
            component.draw();
        }
    }
}
