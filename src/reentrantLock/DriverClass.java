package reentrantLock;

import basicMultithreading.threads.PrintCharctersThread;
import basicMultithreading.threads.PrintEvenNumbersThread;
import basicMultithreading.threads.PrintOddNumbersThread;

public class DriverClass {
    public  static void main(String[] args) throws InterruptedException {
        ReentrantLockBasic instance = new  ReentrantLockBasic();
        PrintOddNumbersThread<ReentrantLockBasic> pon = new PrintOddNumbersThread<>(instance);
        PrintEvenNumbersThread<ReentrantLockBasic> pen = new PrintEvenNumbersThread<>(instance);
        PrintCharctersThread<ReentrantLockBasic> pc = new PrintCharctersThread<>(instance);
        Thread t1 = new Thread(pon);
        Thread t2 = new Thread(pen);
        Thread t3 = new Thread(pc);
        t1.start();
        t2.start();
        t3.start();
        int c = 5;
        while(c>0){
            System.out.println("Main Thread");
            c--;
        }
        t1.join();
        t3.join();
        System.out.println("Counter1 :- "+instance.getCounter1());
        System.out.println("Counter3 :- " + instance.getCounter3());
/*        t2.stop();*/

    }
}
