package lsp.violate;
public class Car extends Vehicle {
    @Override
    public int getNumberOfWheels() {
        return 4;
    }

    @Override
    public String getEngineType() {
        return "Diesel";
    }
}
