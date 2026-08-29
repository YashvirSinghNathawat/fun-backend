package Pojo_Enum_SingleTon.Enum;
public enum EnumWithCustomValues {
    SUNDAY(1),
    MONDAY(2),
    TUESDAY(3),
    WEDNESDAY(4),
    THURSDAY(5),
    FRIDAY(6),
    SATURDAY(7);

    private int val; // Shared by all enum constants

    EnumWithCustomValues(int val) { // Private constructor
        this.val = val;
    }

    public int getVal() {
        return this.val;
    }

    public static EnumWithCustomValues getEnumFrCustomValues(int value) {
        for (EnumWithCustomValues s: EnumWithCustomValues.values()) {
            if (s.val == value) {
                return s;
            }
        }
        return null;
    }

}