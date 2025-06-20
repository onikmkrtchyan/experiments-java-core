package AbstractClass;

abstract class Animal {
    public static final int a = 4;

    void func() {
        System.out.println("Animal walking");
    }

    abstract void a();

    static void f() {
    }
}

public class Rabbit extends Animal {

    @Override
    void a() {
        System.out.println("Rabbit hopping");
    }

    public void func() {
        super.func();
        System.out.println("Rabbit running");
    }

    public static void main(String[] args) {
//        Animal animal = new Animal(); Abstract classes cannot be instantiated

        Rabbit rabbit = new Rabbit();
        rabbit.func();
        rabbit.a();
    }
}
