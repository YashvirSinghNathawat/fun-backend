package Enum;

interface MyInterface {
    public String toLowerCase();
}

public enum EnumImplementsInterface implements MyInterface{
    SUNDAY,
    MONDAY;

    @Override
    public String toLowerCase() {
        return this.name().toLowerCase();
    }
}
