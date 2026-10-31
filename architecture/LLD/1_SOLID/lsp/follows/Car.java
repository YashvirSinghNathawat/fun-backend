package lsp.follows;


public class Car extends EngineVehicle{
    @Override
    public int getNumberOfWheels() {
        return 4;
    }

    @Override
    public String getEngineType() {
        return "Diesel";
    }
}
