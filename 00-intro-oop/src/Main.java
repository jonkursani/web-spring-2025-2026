class Person {
    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}

class Animal {
    void makeSound() {
        System.out.println("Animal makes a sound");
    }
}

class Dog extends Animal {
    void bark() {
        System.out.println("Dog barks");
    }

    @Override
    void makeSound() {
        System.out.println("Woof");
    }
}

class Cat extends Animal {
    @Override
    void makeSound() {
        System.out.println("Meow");
    }
}

abstract class Shape {
    abstract void draw();
}

class Circle extends Shape {
    @Override
    void draw() {
        System.out.println("Drawing a circle");
    }
}

// Interface
interface Drawable {
    void draw();
}

class Square implements Drawable {
    @Override
    public void draw() {
        System.out.println("Drawing a square");
    }
}

public class Main {
    public static void main(String[] args) {
        // Encapsulation
//        Person person = new Person();
//        person.setName("Jon");
//        person.setAge(26);
//        System.out.println(person.getName() + " is " + person.getAge());


        // Inheritance "is a"
//        Dog dog = new Dog();
//        dog.makeSound();
//        dog.bark();

        // Polymorphism
//        Animal dog = new Dog();
//        Animal cat = new Cat();
//        dog.makeSound();
//        cat.makeSound();

        // Abstraction
        Drawable square = new Square();
        square.draw();
    }
}