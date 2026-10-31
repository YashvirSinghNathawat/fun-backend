package lsp.follows;

import java.util.ArrayList;
import java.util.List;


public class Main {
    public static void main(String[] args) {
        List<Vehicle> vehicles = new ArrayList<>();
        vehicles.add(new Motorcycle());
        vehicles.add(new Car());
        vehicles.add(new Bicycle());

        // Gives Compilation Erros as Bicycle has no engine type
        // vehicles.forEach(vehicle -> System.out.println(vehicle.getEngineType().toUpperCase()));
        vehicles.forEach(vehicle -> System.out.println(vehicle.getNumberOfWheels()));
        List<EngineVehicle> ev = new ArrayList<>();
        ev.add(new Motorcycle());
        ev.add(new Car());
        // ev.add(new Bicycle());

        ev.forEach(vehicle -> System.out.println(vehicle.getEngineType()));
    }
}
