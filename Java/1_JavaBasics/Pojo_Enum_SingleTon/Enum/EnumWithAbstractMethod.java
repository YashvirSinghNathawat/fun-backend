package Enum;

public enum EnumWithAbstractMethod {
    SUNDAY {
        public void dummyMethod() {
            System.out.println("SUNDAY dummy Method");
        }
    },
    MONDAY {
        public void dummyMethod() {
            System.out.println("MONDAY dummy Method");
        }
    };

    public abstract void dummyMethod();
}
