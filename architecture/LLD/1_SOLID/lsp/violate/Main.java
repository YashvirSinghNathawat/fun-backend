package lsp.violate;

import java.util.List;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        List<Vehicle> vehicles = new ArrayList<>();
        vehicles.add(new Motorcycle());
        vehicles.add(new Car());
        vehicles.add(new Bicycle());

        // Gives NPE for Bicycle
        vehicles.forEach(vehicle -> System.out.println(vehicle.getEngineType().toUpperCase()));
    }
}
