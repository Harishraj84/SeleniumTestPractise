package org.example;

public  class Example {
    Example() {
        int a = 11;
    }
    static int b = 40;
    int a = 10;

    public void method1() {
        Example example = new Example();
        Example.b = Example.b + 1;
        System.out.println(Example.b);
    }

    public static void main() {
        Example example = new Example();
        example.method1();
        System.out.println(Example.b);
        Example.b = Example.b +10;
        System.out.println(Example.b);
    }
}
