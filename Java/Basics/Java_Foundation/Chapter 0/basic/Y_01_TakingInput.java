package basic;
/*
 * Naming Conventions: Class names should be in PascalCase, while variable and method names should be in camelCase.
 * 
*/
import java.util.Scanner;

public class Y_01_TakingInput {
    public static void main(String[] args) {
        // System is a class in the java.lang package that provides various methods for input and output operations.
        // out is a static member of the System class that represents the standard output stream (console).
        // println is a method of the PrintStream class (which is the type of System.out) that prints a message to the console and moves to the next line.

        // Read input from userj
        Scanner sc = new Scanner(System.in);
        System.out.println("Hello, World!");
        int a = sc.nextInt();
        System.out.println("a : " + a);
        float b = sc.nextFloat();
        System.out.println("b : " + b);

        // hasNextInt does not consume
        if (sc.hasNextInt()) {
            int c = sc.nextInt();
            System.out.println("c : " + c);
        }

        // Clear Buffer
        sc.nextLine();

        String str = sc.next();
        System.out.println("str : " + str);

        // Clear Buffer
        sc.nextLine();
        String str_1 = sc.nextLine();
        System.out.println("str_1 : " + str_1);
    }
}