package Reflections;

public class Bird {
    public String breed;
    private boolean canSwim;

    public void fly() {
        System.out.println("Fly");
    }

    public void eat() {
        System.out.println("eat");
    }
    public void sing(int a, boolean b, String c) {
        System.out.println("Integer " + a + " : Boolean " + b + " : String " + c);
    }
}
