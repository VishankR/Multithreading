package executorsSemaphorePhaserCountDownLatchBarriers;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

import constants.ConsoleTextColors;

public class CyclicBarrierExample {
	/*
	 * A CyclicBarrier is a synchronizer that allows a set of threads to wait for
	 * each other to reach a common execution point, also called a barrier. The
	 * barrier is called cyclic because it can be re-used after the waiting threads
	 * are released.
	 */
	public static void main(String[] args) {
		ExecutorService execService = Executors.newFixedThreadPool(10);
		CyclicBarrier barrier = new CyclicBarrier(3);
		/*
		 * Instead of define a new class for the task we can use lambda Expression.
		 */
		IntStream.range(0, 3).forEach(i -> execService.submit(()->{
			try {
				System.out.println(ConsoleTextColors.TEXT_RED + "Waiting for other threads to reach at this point." + ConsoleTextColors.TEXT_RESET);
				barrier.await();
				System.out.println(ConsoleTextColors.TEXT_GREEN + "Reached to common point." + ConsoleTextColors.TEXT_RESET);
			} catch (InterruptedException | BrokenBarrierException e) {
				e.printStackTrace();
			}
		}));
		execService.shutdown();
	}

}
