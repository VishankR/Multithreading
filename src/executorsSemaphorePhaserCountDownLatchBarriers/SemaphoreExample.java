package executorsSemaphorePhaserCountDownLatchBarriers;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.stream.IntStream;

public class SemaphoreExample {
	/*
	 * We can use semaphores to limit the number of concurrent threads accessing a
	 * specific resource.
	 */
	
	/*
	 * Mutex Explained - What is the problem precisely that the Mutexes are the solution for?  
	 * Let’s imagine a Scrum daily meeting in the morning with five
	 * developers and the Scrum Master. One of them is drinking his coffee and he’s
	 * unable to speak until fully awaken. The other would like to go back to the
	 * desk to finish that SQL query rather than being in a meeting. The rest (all
	 * three) understand the goal of the meeting and are excited to talk about what
	 * they did so they start to speak simultaneously.
	 * 
	 * This ends up chaos and no one understands anything. The Scrum Master steps up
	 * and gives a ball to one of the developers and says: “Only the one with the
	 * ball can talk!”. From that point on, they understood each other and could
	 * conclude the meeting rapidly. If we think about it the SM solved the
	 * situation by defining the critical section in the system. We understand the
	 * word “critical” because we can see where it leads us if we don’t manage it
	 * properly. The developers (actors) used the ball as a tool to signal the
	 * others who can speak and who must wait for it. In other words, the ball
	 * mutually excluded the developers to speak. This naming is so common that
	 * people started to abbreviate: Mutex (Mutual Exclusion).
	 */
	
	/*
	 * Mutex acts similarly to a binary semaphore, we can use it to implement mutual
	 * exclusion.
	 */
	
	public static void main(String[] args) {
		Semaphore semaphore = new Semaphore(3);
		ExecutorService execService = Executors.newFixedThreadPool(4);
		IntStream.range(0, 10).forEach(i -> execService.execute(new Task(semaphore)));
		execService.shutdown();
	}
	static class Task implements Runnable{
		private Semaphore semaphore;
		@Override
		public void run() {
			//Some processing
			try {
				semaphore.acquire();
				System.out.println("Available Permits " + semaphore.availablePermits());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			//IO Call to slow Services
			semaphore.release();
			
			//rest of the processing.
		}
		public Task(Semaphore semaphore) {
			this.semaphore = semaphore;
		}
	}
}
