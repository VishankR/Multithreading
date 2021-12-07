package reentrantLock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ProducerConsumer {

    private Lock lock = new ReentrantLock();
    private Condition cond  = lock.newCondition();
    public void producer() throws InterruptedException {
        lock.lock();
        System.out.println("Producer Method Called.");
        cond.await();
        System.out.println("Producer Again");
        lock.unlock();
    }
    public void consumer() throws InterruptedException {
        Thread.sleep(2000);
        lock.lock();
        System.out.println("Consumer Method Called.");
        cond.signal();
        System.out.println("Consumer Again");
        lock.unlock();
    }
    public static void main(String[] args) throws InterruptedException {
        ProducerConsumer instance = new ProducerConsumer();
        Thread t = new Thread(()->{
            while(true){
                try {
                    instance.producer();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t.start();
        while (true){
            instance.consumer();
        }
    }
}
