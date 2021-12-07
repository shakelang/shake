Compiled from "Foo.java"
public class Foo implements java.lang.Runnable {
  int i;

  static int a;

  public Foo();
    Code:
       0: aload_0
       1: invokespecial #1                  // Method java/lang/Object."<init>":()V
       4: aload_0
       5: iconst_0
       6: putfield      #7                  // Field i:I
       9: return

  public static void main(java.lang.String[]);
    Code:
       0: getstatic     #13                 // Field java/lang/System.out:Ljava/io/PrintStream;
       3: ldc           #19                 // String This is a simple example of decompilation using javap
       5: invokevirtual #21                 // Method java/io/PrintStream.println:(Ljava/lang/String;)V
       8: invokestatic  #27                 // Method a:()V
      11: invokestatic  #30                 // Method b:()V
      14: return

  public static void a();
    Code:
       0: getstatic     #13                 // Field java/lang/System.out:Ljava/io/PrintStream;
       3: ldc           #33                 // String Now we are calling a function...
       5: invokevirtual #21                 // Method java/io/PrintStream.println:(Ljava/lang/String;)V
       8: return

  public static void b();
    Code:
       0: getstatic     #13                 // Field java/lang/System.out:Ljava/io/PrintStream;
       3: ldc           #35                 // String ...and now we are calling b
       5: invokevirtual #21                 // Method java/io/PrintStream.println:(Ljava/lang/String;)V
       8: return

  public void run();
    Code:
       0: return

  static {};
    Code:
       0: iconst_0
       1: putstatic     #37                 // Field a:I
       4: return
}
