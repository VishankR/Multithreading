package basicMultithreading.waitAndNotify;


public class Data {

    private String packet;

    // True if receiver should wait
    // False if sender should wait
    private boolean transfer = true;

    public synchronized void send(String packet) {
        while (!transfer) {
            try {
                /*1. Simply put, calling wait() forces the current thread to wait until some other thread
                     invokes notify() or notifyAll() on the same object(As here instance of "Data" class).
                  2. We placed these methods inside synchronized methods to provide intrinsic locks.
                     If a thread calling wait() method does not own the inherent lock, an error will be thrown.
                  3. The major difference is that wait() releases the lock or monitor while sleep() doesn’t releases
                     the lock or monitor */
                wait();
            } catch (InterruptedException e)  {
                Thread.currentThread().interrupt();
                System.out.println("Send method Thread interrupted");
            }
        }
        transfer = false;

        this.packet = packet;
        //This method simply wakes all threads that are waiting on this object's monitor.
        notifyAll();

        /*For all threads waiting on this object's monitor (by using any one of the wait() methods),
        the method notify() notifies any one of them to wake up arbitrarily.*/
        //notify();
    }

    public synchronized String receive() {
        while (transfer) {
            try {
                wait();
            } catch (InterruptedException e)  {
                Thread.currentThread().interrupt();
                System.out.println("receive method Thread interrupted");
            }
        }
        transfer = true;

        notifyAll();
        return packet;
    }
    /*Let's break down what's going on here:

    1. The packet variable denotes the data that is being transferred over the network.
    2. We have a boolean variable transfer, which the Sender and Receiver will use for synchronization:
        i - If this variable is true, the Receiver should wait for Sender to send the message.
        ii - If it's false, Sender should wait for Receiver to receive the message.
    3. The Sender uses send() method to send data to the Receiver:
        i - If transfer is false, we'll wait by calling wait() on this thread.
        ii - But when it is true, we toggle the status, set our message and call notifyAll() to wake up other
             threads to specify that a significant event has occurred and they can check if they can continue execution.
    4. Similarly, the Receiver will use receive() method:
        i - If the transfer was set to false by Sender, only it will proceed, otherwise we'll call wait() on this thread.
        ii - When the condition is met, we toggle the status, notify all waiting threads to wake up and return the data
             packet that was Receiver.*/
}
