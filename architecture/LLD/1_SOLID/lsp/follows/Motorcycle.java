package lsp.follows;
public class Motorcycle extends EngineVehicle {
    @Override 
    public int getNumberOfWheels(){
        return 2;
    }

    @Override 
    public String getEngineType() {
        return "Petrol";
    }
}
