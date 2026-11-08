public abstract class ToppingDecorator implements BasePizza{
    BasePizza basepizza;

    ToppingDecorator(BasePizza pizza) {
        basepizza = pizza;
    }
}
