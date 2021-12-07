package reentrantLock;
import basicMultithreading.Counter;
import constants.ConsoleTextColors;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockBasic extends Counter {
/*    1. The ReentrantLock class implements the Lock interface and provides synchronization to methods while accessing shared resources.
       The code which manipulates the shared resource is surrounded by calls to lock and unlock method. This gives a lock to the
       current working thread and blocks all other threads which are trying to take a lock on the shared resource.
    2. As the name says, ReentrantLock allows threads to enter into the lock on a resource more than once.
        When the thread first enters into the lock, a hold count is set to one. Before unlocking the thread can re-enter into lock
        again and every time hold count is incremented by one. For every unlocks request, hold count is decremented by one and when
        hold count is 0, the resource is unlocked.
    3. Reentrant Locks also offer a fairness parameter, by which the lock would abide by the order of the lock request i.e.
       after a thread unlocks the resource, the lock would go to the thread which has been waiting for the longest time.
       This fairness mode is set up by passing true to the constructor of the lock.
    4. Unlock statement is always called in the finally block to ensure that the lock is released even if an exception is
       thrown in the method body(try block).
    5. A ReentrantLock is unstructured, unlike synchronized constructs -- i.e. you don't need to use a block structure for
       locking and can even hold a lock across methods.
    6. The Condition class provides the ability for a thread to wait for some condition to occur while executing the critical section.
       This can occur when a thread acquires the access to the critical section but doesn't have the necessary condition to perform
       its operation. For example, a reader thread can get access to the lock of a shared queue, which still doesn't have any data to
       consume.
       Traditionally Java provides wait(), notify() and notifyAll() methods for thread intercommunication. Conditions have similar
       mechanisms, but in addition, we can specify multiple conditions:
    7. ReentrantLock key features as per this article
       Ability to lock interruptibly.
       Ability to timeout while waiting for lock.
       Power to create fair lock.
       API to get list of waiting thread for lock.
       Flexibility to try for lock without blocking.


    ReentrantLock() Methods

    lock(): Call to the lock() method increments the hold count by 1 and gives the lock to the thread if the shared resource is
            initially free.
    unlock(): Call to the unlock() method decrements the hold count by 1. When this count reaches zero, the resource is released.
    tryLock(): If the resource is not held by any other thread, then call to tryLock() returns true and the hold count is incremented
               by one. If the resource is not free, then the method returns false, and the thread is not blocked, but exits.
               tryLock(long timeout, TimeUnit unit): As per the method, the thread waits for a certain time period as defined by
               arguments of the method to acquire the lock on the resource before exiting.
    lockInterruptibly(): This method acquires the lock if the resource is free while allowing for the thread to be interrupted by
                         some other thread while acquiring the resource. It means that if the current thread is waiting for the lock
                         but some other thread requests the lock, then the current thread will be interrupted and return immediately
                         without acquiring the lock.
    getHoldCount(): This method returns the count of the number of locks held on the resource.
    isHeldByCurrentThread(): This method returns true if the lock on the resource is held by the current thread.*/

    private int counter1 = 0;
    private static int counter2 = 0;
    private int counter3 = 0;

    private ReentrantLock lock = new ReentrantLock();
/*    private final Object obj1 = new Object();
    private static final Object obj2 = new Object();
    private final Object obj3 = new Object();
    private static ReentrantLockBasic instance = null;*/
    public int getCounter1() {
    return counter1;
}
    public void setCounter1(int counter1) {
    this.counter1 = counter1;
}

    public static int getCounter2() {
    return counter2;
}

    public static void setCounter2(int counter2) {
        ReentrantLockBasic.counter2 = counter2;
}

    public int getCounter3() {
    return counter3;
}

    public void setCounter3(int counter3) {
    this.counter3 = counter3;
}

    public boolean incrementCounter1() throws InterruptedException {
        if(lock.tryLock()) {
            try{
                System.out.println(ConsoleTextColors.TEXT_RED + "Have Accquired lock on obj and incrementing counter1" + ConsoleTextColors.TEXT_RESET);
                System.out.println(ConsoleTextColors.TEXT_RED + "Hold Count : " + lock.getHoldCount() +" Owner : "+lock.isHeldByCurrentThread()+ ConsoleTextColors.TEXT_RESET);
                Thread.sleep(5000);
                counter1++;
                return true;
            }finally {
                    lock.unlock();
            }
        }else {
            Thread.sleep(5000);
            System.out.println(ConsoleTextColors.TEXT_RED + "Could not accquired the lock for counter1" + ConsoleTextColors.TEXT_RESET);
            return false;
        }
}
    public boolean incrementCounter2() throws InterruptedException {
        if(lock.tryLock()){
            try{
                System.out.println(ConsoleTextColors.TEXT_BLUE+"Have Accquired lock on obj and incrementing counter2"+ConsoleTextColors.TEXT_RESET);
                System.out.println(ConsoleTextColors.TEXT_BLUE + "Hold Count : " + lock.getHoldCount()+" Owner : "+lock.isHeldByCurrentThread() + ConsoleTextColors.TEXT_RESET);
                Thread.sleep(5000);
                counter2++;
                return true;
            }finally {
                lock.unlock();
            }
        }else {
            Thread.sleep(5000);
            System.out.println(ConsoleTextColors.TEXT_BLUE + "Could not accquired the lock for counter2" + ConsoleTextColors.TEXT_RESET);
            return false;
        }

}
    public boolean  incrementCounter3() throws InterruptedException {
        if(lock.tryLock()){
            try{
                System.out.println(ConsoleTextColors.TEXT_CYAN+"Have Accquired lock on obj and incrementing counter3"+ConsoleTextColors.TEXT_RESET);
                System.out.println(ConsoleTextColors.TEXT_CYAN + "Hold Count : " + lock.getHoldCount()+" Owner : "+lock.isHeldByCurrentThread() + ConsoleTextColors.TEXT_RESET);
                Thread.sleep(5000);
                counter3++;
                return true;
            }finally{
                lock.unlock();
            }
        }else {
            Thread.sleep(5000);
            System.out.println(ConsoleTextColors.TEXT_CYAN + "Could not accquired the lock for counter3" + ConsoleTextColors.TEXT_RESET);
            return false;
        }

}

}
