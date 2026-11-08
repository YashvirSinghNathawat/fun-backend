public class CarFactoryProvider {
    public CarFactory getFactory(CarType cartype) {
        switch(cartype) {
            case ECONOMY:
                return new EconomyCarFactory();
            case LUXURY:
                return new LuxuryCarFactory();
            default:
                throw new IllegalArgumentException("Unknown car type:" + cartype);
        }
    }
}
