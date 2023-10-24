public class ObjectOrientedHelloWorld {

    public static void main(String[] args) {
        System.out.println("Hello World!");

        HelloWorld helloWorld = new HelloWorld();

        helloWorld.sayHello();
    }

    static class HelloWorld {
        public void sayHello() {
            System.out.println("Hello World!");
        }
    }

}