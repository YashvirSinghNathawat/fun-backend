public class Demo {
    public static void main(String[] args) {
        CarFactoryProvider carFactoryProvider = new CarFactoryProvider();
        CarFactory economyCarFactory = carFactoryProvider.getFactory(CarType.ECONOMY);
        economyCarFactory.produceCompleteVehicle();
    }   
}
