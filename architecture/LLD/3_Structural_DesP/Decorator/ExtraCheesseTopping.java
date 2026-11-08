public class ExtraCheesseTopping extends ToppingDecorator{
    ExtraCheesseTopping(BasePizza pizza) {
        super(pizza);
    }

    @Override
    public String getDescription() {
        return basepizza.getDescription() + " + Extra Cheese";
    }

    @Override
    public int getCost() {
        return basepizza.getCost() + 50;
    }
}
