public class MushroomTopping extends ToppingDecorator{
    MushroomTopping(BasePizza pizza) {
        super(pizza);
    }

    @Override
    public String getDescription() {
        return basepizza.getDescription() + " + Extra Mushroom";
    }

    @Override
    public int getCost() {
        return basepizza.getCost() + 30;
    }
}
