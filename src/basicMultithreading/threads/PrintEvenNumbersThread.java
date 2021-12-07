package basicMultithreading.threads;

import basicMultithreading.Counter;

public class PrintEvenNumbersThread<T extends Counter> implements Runnable {
    private T t;
    @Override
    public void run() {
        int i = 10;
        while (i>0){
            if(i%2 == 0){
                try {
                    t.incrementCounter2();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            i--;
        }

    }
    public  PrintEvenNumbersThread(T t){
        this.t = t;
    }
}
