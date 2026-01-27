package memory;

public class MyRunnable implements Runnable{

    /*
    For SeparateObjects - count will be in Heap Memory but two copies since two separate runnables are used
    For SharedObjects - since one runnable is used they both use reference to same count object in heap memory
     */
    private int count = 0;
    /*
    For SeparateObjects & SharedObjects: Both references to separate in heap
     */
    private MyObject myObject_2;
    /*
    For SeparateObjects & SharedObjects: Both references to same in heap
     */
    private MyObject myObject_3;

    public MyRunnable() {

    }

    public MyRunnable(MyObject myObject_3) {
        myObject_2 = new MyObject();
        this.myObject_3 = myObject_3;
    }
    @Override
    public void run() {

        /*
        For SeparateObjects - Object is created on heap and two threads will share the reference
        For SharedObjects - Same as above even when runnables are same
         */
        MyObject myObject_1 = new MyObject();
        System.out.println("myObject1 : " +  myObject_1);
        System.out.println("myObject2 : " +  myObject_2);
        System.out.println("myObject3 : " +  myObject_3);


        /*
        For SeparateObjects and SharedObjects - i will be in Thread Stack separate for each stack
         */
//        synchronized (this) {
            for (int i=0; i< 100000; i++) {
                this.count++;
            }
//        }

        System.out.println(Thread.currentThread().getName() + " : " + this.count);
    }
}
