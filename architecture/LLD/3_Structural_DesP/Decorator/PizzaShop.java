public class PizzaShop {
    public static void main(String[] args) {
        BasePizza pizza1 = new Margurita();
        System.out.println("Order 1: " + pizza1.getDescription() + " = Rs." + pizza1.getCost());
    
        BasePizza pizza2 = new ExtraCheesseTopping(new Margurita()); 
        System.out.println("Order 2: " + pizza2.getDescription() + " = Rs." + pizza2.getCost());
    
        BasePizza pizza3 = new ExtraCheesseTopping(new MushroomTopping(new Margurita()));
        System.out.println("Order 3: " + pizza3.getDescription() + " = Rs." + pizza3.getCost());
    }
}
