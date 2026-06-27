
// One file one public class

class Employee {
    int id;
    String name;
    double salary;

    public static void main(String[] args) {
        // Comments are removed at compile time and not included in the bytecode
        /*
         * 1. javac Employee.java
         * 2. java Employee
         */
        System.out.println("Hello, World!");
    }
}