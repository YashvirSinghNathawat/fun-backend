package memory;

public class SharedObjects {
    public void runSharedObjects() {
        MyObject obj1 = new MyObject();
        MyRunnable r1 = new MyRunnable(obj1);
        Thread t1 = new Thread(r1, "t1");
        Thread t2 = new Thread(r1, "t1");
        System.out.println("-------------------SharedObjects--------------------");
        t1.start();
        t2.start();

        try{
            t1.join();
            t2.join();
        } catch (InterruptedException e){
            System.out.println(e.getMessage());
        }
    }
}
