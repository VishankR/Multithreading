package deadLock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DeadLockExample {
	static Lock lock1 = new ReentrantLock();
	static Lock lock2 = new ReentrantLock();
	public static void main(String[] args) {
		Thread t1 = new Thread(() -> {
			DeadLockExample dle = new DeadLockExample();
			dle.processThis();
		});
		Thread t2 = new Thread(() -> {
			DeadLockExample dle = new DeadLockExample();
			dle.processThat();
		});
		t1.start();
		t2.start();
	}
	public void processThis() {
		lock1.lock();
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		lock2.lock();
		lock1.unlock();
		lock2.unlock();
	}
	public void processThat() {
		lock2.lock();
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		lock1.lock();
		lock2.unlock();
		lock1.unlock();
	}
}
