package synchronizedBlock;

public class SharedMonitorObjectMain {
    public static void main(String[] args) {
        Object monitor1 = new Object();
        SharedMonitorObject smo1 = new SharedMonitorObject(monitor1);
        SharedMonitorObject smo2 = new SharedMonitorObject(monitor1);

        /*
        smo1.incCounter and smo2.incCounter will be blocked as same monitor object is used.
         */

        Object monitor2 = new Object();
        SharedMonitorObject smo3 = new SharedMonitorObject(monitor2);
        /*
        But smo1.incCounter and smo3.incCounter can be executed at same time
         */

        /*
        **Disclaimer** Do not use string as monitor object
         */
        SharedMonitorObject smo4 = new SharedMonitorObject("mon3");
        SharedMonitorObject smo5 = new SharedMonitorObject("mon3");
        // Depends on compiler how it is stored
    }
}
