public class EconomyCarFactory implements CarFactory{
    @Override 
    public CarInterior createInterior() {
        return new EconomyCarInterior();
    }

    @Override 
    public CarExterior createExterior() {
        return new EconomyCarExterior();
    }
}
