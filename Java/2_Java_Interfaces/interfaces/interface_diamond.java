package Java.2_Java_Interfaces.interfaces;

interface LandAnimal {
    public boolean canBreathe();
}

interface WaterAnimal {
    int constant;   // Public static and final allowed variables
    /*
        Before Java 8 - public methods are allowed.
        Java 8 - static methods are allowed
        Java 9 - private are allowed.
    */
    public boolean canBreathe();  
}
public class interface_diamond implements LandAnimal, WaterAnimal{
    @Override
    public boolean canBreathe() {  // Cannot reduce visibility to default
        return true;
    }
}
