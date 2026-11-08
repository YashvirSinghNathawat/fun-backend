public interface CarFactory {
    
    CarInterior createInterior();
    CarExterior createExterior();

    default void produceCompleteVehicle() {
        System.out.println("Starting complete vehicle production...");
        CarInterior interior = createInterior();
        CarExterior exterior = createExterior();

        // Assemble Car
        interior.addInteriorComponents();
        exterior.addExteriorComponents();

        System.out.println("Vehicle production completed!");
    }
}
