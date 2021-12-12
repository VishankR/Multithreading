package basicMultithreading;

import basicMultithreading.intrinsicLockWithCustomObjectAndSleepMethod.CountingClass;
import basicMultithreading.threads.PrintCharctersThread;
import basicMultithreading.threads.PrintOddNumbersThread;
import basicMultithreading.threads.PrintEvenNumbersThread;

public class DriverClass {
    public static void main(String[] args) {
        CountingClass cc = new CountingClass();
        PrintOddNumbersThread<CountingClass> pon = new PrintOddNumbersThread<>(cc);
        PrintEvenNumbersThread<CountingClass> pen = new PrintEvenNumbersThread<>(cc);
        PrintCharctersThread<CountingClass> pc = new PrintCharctersThread<>(cc);
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
        try {
			t1.join();
		} catch (InterruptedException e) {
			System.out.println("Exception ocurred in PrintOddNumbersThread");
			e.printStackTrace();
		}
        try {
			t3.join();
		} catch (InterruptedException e) {
			System.out.println("Exception ocurred in PrintCharctersThread");
			e.printStackTrace();
		}
        System.out.println("Counter1 :- "+cc.getCounter1());
        System.out.println("Counter3 :- " + cc.getCounter3());
        t2.stop();
    }
}
