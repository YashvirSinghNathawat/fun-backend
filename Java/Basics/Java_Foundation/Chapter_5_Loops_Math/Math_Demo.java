import java.lang.Math;

public class Math_Demo {
    public static void main() {
        int abs1 = Math.abs(-10);
        double abs2 = Math.abs(-20.0);
        // Math.abs is overloaded in 4 ways depends on parameter(int, long, float, double)

        double ceil = Math.ceil(7.343);
        double floor = Math.floor(7.343);

        // FloorDiv
        double result1 = Math.floorDiv(100,9);
        double result2 = 100/9;
        double result3 = Math.floorDiv(-100,9);
        double result4 = -100/9;
        System.out.println(result1);
        System.out.println(result2);
        System.out.println(result3);
        System.out.println(result4);

        int min = Math.min(10, 20);
        int max = Math.max(10, 20);
        double roundedDown = Math.round(23.445);
        double roundedUp   = Math.round(23.545);

        double random = Math.random();
        double random_1 = Math.random() * 100D;

        double exp2 = Math.exp(2); //e^2

        double log10_1   = Math.log10(1);

        double pow8 = Math.pow(2,8);

        double sqrt9 = Math.sqrt(9);

        double sin = Math.sin(Math.PI);


    }
}

