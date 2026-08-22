package Enum;

public enum EnumMethodOverride {
    SUNDAY {
        public void dummyMethod() {
            System.out.println("SUNDAY dummy Method");
        }
    },
    MONDAY,
    TUESDAY,
    WEDNESDAY,
    THURSDAY,
    FRIDAY,
    SATURDAY;

    public void dummyMethod() {
        System.out.println("EnumMethodOverride dummy Method");
    }
}