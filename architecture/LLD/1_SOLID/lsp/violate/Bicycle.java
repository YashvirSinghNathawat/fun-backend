package lsp.violate;
public class Bicycle extends Vehicle{
    
    // Violates LSP: parent contract implies a valid engine type, bicycle returns null
    @Override
    public String getEngineType() {
        return null;
    }
}
