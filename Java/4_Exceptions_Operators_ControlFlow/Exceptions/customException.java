class myCustomException extends Exception{
    myCustomException(String message) {
        super(message);
    }
}

class myCustomException_Unchecked extends RuntimeException {
    myCustomException_Unchecked(String message) {
        super(message);
    }
}

public class customException {

    private static void method1() throws myCustomException{
        throw new myCustomException("This is my custom Exception");
    }

    private static void method2() {
        throw new myCustomException_Unchecked("This is my custom Exception");
    }

    public static void main(String[] args) {
        try {
            method1();
        } 
        catch(myCustomException e) {
            System.out.println(e.getMessage());
        }
        method2();
    }
}
