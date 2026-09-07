import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class CheckedExceptions {
    private static void classNotFoundException() {
        try {
            Class.forName("Yashvir");
        }
        catch (ClassNotFoundException e) {
            System.out.println("Class Yashvir not found");
        }
    }

    private static void interruptedException() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println("Thread interrupted");
        }
    }

    private static void ioException() {
        try {
            new FileInputStream("abc.txt");
        }
        catch (FileNotFoundException e) {
            System.out.println("File not found!");
        }
    }

    private static void method1() {
        // Runtime Exception
        throw new ClassCastException();
    }

    private static void method2() throws ClassNotFoundException{
        throw new ClassNotFoundException();
    }

    public static void main(String[] args) {
        classNotFoundException();
        interruptedException();
        ioException();
    }

}
