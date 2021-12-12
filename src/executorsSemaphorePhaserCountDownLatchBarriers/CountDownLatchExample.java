package executorsSemaphorePhaserCountDownLatchBarriers;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountDownLatchExample {

	/*
	 * A synchronization aid that allows one or more threads to wait until a set of
	 * operations being performed in other threads completes. A CountDownLatch is
	 * initialized with a given count. The await methods block until the current
	 * count reaches zero due to invocations of the countDown() method, after which
	 * all waiting threads are released and any subsequent invocations of await
	 * return immediately. This is a one-shot phenomenon -- the count cannot be
	 * reset. If you need a version that resets the count, consider using a
	 * CyclicBarrier.
	 * 
	 * A CountDownLatch is a versatile synchronization tool and can be used for a
	 * number of purposes. A CountDownLatch initialized with a count of one serves
	 * as a simple on/off latch, or gate: all threads invoking await wait at the
	 * gate until it is opened by a thread invoking countDown(). A CountDownLatch
	 * initialized to N can be used to make one thread wait until N threads have
	 * completed some action, or some action has been completed N times.
	 * 
	 * A useful property of a CountDownLatch is that it doesn't require that threads
	 * calling countDown wait for the count to reach zero before proceeding, it
	 * simply prevents any thread from proceeding past an await until all threads
	 * could pass.
	 */
	
	public static void main(String[] args) {
		ExecutorService execService = Executors.newFixedThreadPool(4);
		// Setting countDownlatch to 3 as We have 3 dependent service. Only after their initialization we will let our main thread run.
		CountDownLatch latch = new CountDownLatch(3);
		execService.submit(new DependentService(latch));
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		execService.submit(new DependentService(latch));
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		execService.submit(new DependentService(latch));
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		try {
			System.out.println("Waiting for dependent threads to be initialized.");
			latch.await();
			System.out.println("now processing further with main thread.");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static class DependentService implements Runnable{
		private CountDownLatch countDownLatch;
		public DependentService(CountDownLatch countDownLatch) {
			this.countDownLatch = countDownLatch;
		}
		@Override
		public void run() {
			//startup Task
			countDownLatch.countDown();
			//continue with other operations.
			System.out.println(" Value of countDownLatch is " + countDownLatch.getCount());
		}
	}

}
