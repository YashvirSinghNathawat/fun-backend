
public class UncheckedExceptions {
    private static void arithmeticException() {
        int a = 5 / 0;
    }

    private static void illegalArgument() {
        int a = Integer.parseInt("abc");
    }

    private static void nullPointerException() {
        String a = null;
        char b = a.charAt(0);
    }

    private static void classCastException() {
        Object a = 3;
        String b = (String) a;
    }

    private static void indexOutofBound() {
        int[] a = new int[2];
        int b = a[3];
    }

    public static void main(String[] args) {
        // arithmeticException();
        // illegalArgument();
        // nullPointerException();
        // classCastException();
        indexOutofBound();
    }

}
