package basicMultithreading.intrinsicLockWithCustomObjectAndSleepMethod;

import basicMultithreading.Counter;
import constants.ConsoleTextColors;

public class CountingClass extends Counter {

    private int counter1 = 0;
    private static int counter2 = 0;
    private int counter3 = 0;

    private final Object obj1 = new Object();
    private static final Object obj2 = new Object();
    private final Object obj3 = new Object();
    private static CountingClass instance = null;
    public int getCounter1() {
        return counter1;
    }

    /*private CountingClass(){

    }*/
    public void setCounter1(int counter1) {
        this.counter1 = counter1;
    }

    public static int getCounter2() {
        return counter2;
    }

    public static void setCounter2(int counter2) {
        CountingClass.counter2 = counter2;
    }

    public int getCounter3() {
        return counter3;
    }

    public void setCounter3(int counter3) {
        this.counter3 = counter3;
    }

    public boolean incrementCounter1() throws InterruptedException {
/*        If your resource R is java monitor, then there are only two ways to release it:
          1. exit synchronized block
          2. call wait on owned monitor
          */

//        1. Rule of thumb  - We syncronize only necessary part of code (It is not necessary to syncronize whole method.)
//        2. By using "this" as a parameter we are taking the lock on whole Object.So no thread will be able to call any other method
//           as well whether they are affecting the same variable or not.
        /*synchronized (this){
            while(counter1 <= upperLimit)
            counter1++;
        }*/
//        3. So we can overcome this problem using two different Objects.
//        4. when "obj1" is not final and we assign a new Object to it. Here we're synchronizing on an object that we're no longer holding
//           a reference to. Consider another thread running this method: they may enter and try to hit the lock at the moment after the
//           reference to obj has been updated to point to the new object. At that point, they're synchronizing on a different object than
//           the first thread. This is probably not what you're expecting. Unless you have a good reason not to, you probably want to
//           synchronize on a final Object
        synchronized (obj1){
            //obj1 = new Object();
            System.out.println(ConsoleTextColors.TEXT_RED+"Have Accquired lock on obj1 and incrementing counter1"+ConsoleTextColors.TEXT_RESET);
/*            1. Thread.sleep causes the current thread to suspend execution for a specified period. This is an efficient means of making
            processor time available to the other threads of an application or other applications that might be running on a computer system. Means
            During this time, it's not consuming CPU time, so the CPU can be executing other tasks.
            2. The Thread.sleep() method essentially interacts with the thread scheduler to put the current thread into a wait state for the
            required interval. The thread however does not lose ownership of any monitors.*/
            Thread.sleep(10000);
            counter1++;
            return  true;
        }
    }
    public boolean incrementCounter2() throws InterruptedException {
        /*synchronized (CountingClass.class){
            counter2++;
        }*/
        synchronized (obj2){
            System.out.println(ConsoleTextColors.TEXT_BLUE+"Have Accquired lock on obj2 and incrementing counter2"+ConsoleTextColors.TEXT_RESET);
            Thread.sleep(2000);
            counter2++;
            return true;
        }
    }
    public boolean  incrementCounter3() throws InterruptedException {
        synchronized (obj1){
            System.out.println(ConsoleTextColors.TEXT_CYAN+"Have Accquired lock on obj1 and incrementing counter3"+ConsoleTextColors.TEXT_RESET);
            Thread.sleep(10000);
            counter3++;
            return true;
        }
    }
/*    public static CountingClass getInstance(){
        if(null == instance ){
            instance = new CountingClass();
        }
        return  instance;
    }*/


}
