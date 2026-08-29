import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import Reflections.Bird;
import Reflections.SingleTonBreak;

public class ReflectionJava {
    public static void main(String[] args) throws Exception {

        /* Getting Class reference - 3 ways */
        Class<Bird> birdClass = (Class<Bird>) Class.forName("Reflections.Bird");
        birdClass = Bird.class;
        birdClass = (Class<Bird>) new Bird().getClass();
        System.out.println("Class Name: " + birdClass.getName());
        System.out.println("Modifiers: " + Modifier.toString(birdClass.getModifiers()));

        /* Reflection of Methods */
        System.out.println("\n--- Methods ---");
        Method[] methods = birdClass.getDeclaredMethods();
        for (Method method : methods) {
            System.out.println("Name       : " + method.getName());
            System.out.println("Return Type: " + method.getReturnType());
            System.out.println("Declared In: " + method.getDeclaringClass());
            System.out.println("**********************");
        }

        /* Invoking a Method using Reflection */
        System.out.println("\n--- Invoking sing() ---");
        Bird eagleObject = birdClass.getDeclaredConstructor().newInstance();
        Method singMethod = birdClass.getMethod("sing", int.class, boolean.class, String.class);
        singMethod.invoke(eagleObject, 1, true, "Yashvir");

        /* Reflection of Fields */
        System.out.println("\n--- Fields ---");
        Field[] fields = birdClass.getDeclaredFields();
        for (Field field : fields) {
            System.out.println("Name       : " + field.getName());
            System.out.println("Type       : " + field.getType());
            System.out.println("Declared In: " + field.getDeclaringClass());
            System.out.println("**********************");
        }

        /* Setting value of public field */
        System.out.println("\n--- Setting Public Field (breed) ---");
        Bird birdObject = birdClass.getDeclaredConstructor().newInstance();
        Field breedField = birdClass.getField("breed");
        breedField.set(birdObject, "EagleBreed");
        System.out.println("breed: " + birdObject.breed);

        /* Setting value of private field */
        System.out.println("\n--- Setting Private Field (canSwim) ---");
        Field canSwim = birdClass.getDeclaredField("canSwim");
        canSwim.setAccessible(true);
        canSwim.set(birdObject, true);
        System.out.println("canSwim: " + canSwim.getBoolean(birdObject));

        /* Reflection of Constructor - Breaking Singleton */
        System.out.println("\n--- Breaking Singleton via Constructor Reflection ---");
        Class<SingleTonBreak> singleTonClass = (Class<SingleTonBreak>) Class.forName("Reflections.SingleTonBreak");
        Constructor[] constructors = singleTonClass.getDeclaredConstructors();
        for (Constructor constructor : constructors) {
            System.out.println("Constructor Modifier: " + Modifier.toString(constructor.getModifiers()));
            constructor.setAccessible(true);
            SingleTonBreak instance = (SingleTonBreak) constructor.newInstance();
            instance.fly();
        }
    }
}
