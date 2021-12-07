package basicMultithreading.threads;

import basicMultithreading.Counter;


public class PrintCharctersThread<T extends Counter> implements  Runnable{
    private T t;
    @Override
    public void run() {
        int i = 65;
        while (i<=75){
            try {
                t.incrementCounter3();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            i++;
        }
    }
    public  PrintCharctersThread(T t){
        this.t = t;
    }
}
