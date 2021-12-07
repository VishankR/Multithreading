package basicMultithreading.threads;

import basicMultithreading.Counter;
import basicMultithreading.intrinsicLockWithCustomObjectAndSleepMethod.CountingClass;


public class PrintOddNumbersThread<T extends Counter> implements Runnable {
    private T t;
    @Override
    public void run() {
        int i = 10;
        while (i>0){
            if(i%2 !=0 ){
                try {
                    t.incrementCounter1();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            i--;
        }

    }
    public  PrintOddNumbersThread(T t){
        this.t=t;
    }
}
