import Enum.*;

public class EnumDemo {

    public static void main(String args[]) {
        for(EnumSample s: EnumSample.values()) {
            System.out.println(s + ": " + s.ordinal());
        }

        EnumSample enumVariable = EnumSample.valueOf("SUNDAY");
        System.out.println(enumVariable);

        /* Enum With Custom Values */
        for (EnumWithCustomValues s : EnumWithCustomValues.values()) {
            System.out.println(s + ": " + s.getVal());
        }
        System.out.println(EnumWithCustomValues.FRIDAY.getVal());
        System.out.println(EnumWithCustomValues.getEnumFrCustomValues(2));

        // EnumMethodOverride
        EnumMethodOverride.FRIDAY.dummyMethod();
        EnumMethodOverride.SUNDAY.dummyMethod();

        // EnumImplementsInterface
        System.out.println(EnumImplementsInterface.SUNDAY.toLowerCase());
        
    }
    
}

