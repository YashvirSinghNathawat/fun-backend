package memory;

public class SeparateObjects {

    public void runSeparateObjects() {
        int myLocalvar = 0;  //Local to Thread in this case Main thread
        String myLocalString = "Text";  // String object Text is stored in heap

        MyObject obj1 = new MyObject();
        Runnable runnable1 = new MyRunnable(obj1);
        Runnable runnable2 = new MyRunnable(obj1);

        System.out.println("-------------------SeparateObjects--------------------");

        Thread t1 = new Thread(runnable1, "t1");
        Thread t2 = new Thread(runnable2, "t2");

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
