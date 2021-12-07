public class Foo implements Runnable {
    int i = 0;
    static int a = 0;
    public static void main(final String[] args) {
        System.out.println("This is a simple example of decompilation using javap");
        a();
        b();
    }

    public static void a() {
        System.out.println("Now we are calling a function...");
    }

    public static void b() {
        System.out.println("...and now we are calling b");
    }

    @Override
    public void run() {

    }
}