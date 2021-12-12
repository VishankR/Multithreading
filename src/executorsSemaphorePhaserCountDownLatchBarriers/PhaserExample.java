package executorsSemaphorePhaserCountDownLatchBarriers;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Phaser;
import java.util.stream.IntStream;

import constants.ConsoleTextColors;

public class PhaserExample {

	/* Phaser can act as CountDownLatch and CyclicBarrier plus more flexibility. */
	/*
	 * Any thread can register/deregister itself to phaser using register/deregister method. So it is not
	 * necessary to know the parties number while constructing the phaser
	 */
	public static void main(String[] args) {
		ExecutorService execService = Executors.newFixedThreadPool(4);
		Phaser phaser = new Phaser(3); // 3 parties
		//Similer to CountDownLatch
		IntStream.range(0, 3).forEach(i -> execService.submit(() -> {
			System.out.println(ConsoleTextColors.TEXT_RED + phaser.getArrivedParties() + ConsoleTextColors.TEXT_RESET);
			phaser.arrive();
			//phaser.arriveAndAwaitAdvance(); // To work as Cyclic barrier.
			System.out.println(ConsoleTextColors.TEXT_RED + "Arrived." + ConsoleTextColors.TEXT_RESET);
		}));
		phaser.awaitAdvance(0); // 0 - number of phase (which is the current phase of phaser. Starts from 0.)
		System.out.println(ConsoleTextColors.TEXT_GREEN + "Continue the execution of main thread." + ConsoleTextColors.TEXT_RESET);
		execService.shutdown();
	}

}
