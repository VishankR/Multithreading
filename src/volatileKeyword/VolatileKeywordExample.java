package volatileKeyword;

public class VolatileKeywordExample {

	/** Shared Multiprocessor Architecture -
	 * Processors are responsible for executing program instructions. Therefore, 
	 * they need to retrieve both program instructions and required data from RAM. 
	 * As CPUs are capable of carrying out a significant number of instructions per second, fetching from RAM is not that ideal for them. 
	 * To improve this situation, processors are using tricks like Out of Order Execution, Branch Prediction, Speculative Execution, and, 
	 * of course, Caching. As different cores execute more instructions and manipulate more data, they fill up their caches with more 
	 * relevant data and instructions. This will improve the overall performance at the expense of introducing cache coherence challenges.
	 * */
	
	/*
	 * Memory Visibility In this simple example, we have two application threads:
	 * the main thread and the reader thread. Let's imagine a scenario in which the
	 * OS schedules those threads on two different CPU cores, where:
	 * 
	 * The main thread has its copy of ready and number variables in its core cache
	 * The reader thread ends up with its copies, too The main thread updates the
	 * cached values On most modern processors, write requests won't be applied
	 * right away after they're issued. In fact, processors tend to queue those
	 * writes in a special write buffer. After a while, they will apply those writes
	 * to main memory all at once.
	 * 
	 * With all that being said, when the main thread updates the number and ready
	 * variables, there is no guarantee about what the reader thread may see. In
	 * other words, the reader thread may see the updated value right away, or with
	 * some delay, or never at all!
	 * 
	 * This memory visibility may cause liveness issues in programs that are relying
	 * on visibility.
	 */
	
	/*
	 * volatile and Thread Synchronization For multithreaded applications, we need
	 * to ensure a couple of rules for consistent behavior:
	 * 
	 * Mutual Exclusion – only one thread executes a critical section at a time
	 * Visibility – changes made by one thread to the shared data are visible to
	 * other threads to maintain data consistency synchronized methods and blocks
	 * provide both of the above properties, at the cost of application performance.
	 * 
	 * volatile is quite a useful keyword because it can help ensure the visibility
	 * aspect of the data change without, of course, providing mutual exclusion.
	 * Thus, it's useful in the places where we're ok with multiple threads
	 * executing a block of code in parallel, but we need to ensure the visibility
	 * property.
	 */	
	
	/*
	 * In Java when we have multiple threads, each thread have its own stack (a
	 * memory space) and init each thread has its own copy of variables it can
	 * access . If volatile key word is not there to decorate int i ,each thread may
	 * use it in their executions. When declared with volatile, each thread has to
	 * read/write value of i from/to direcltly main memory , not to/from local
	 * copies. So in each threads perspective, operations to/from variable i is
	 * atomic
	 */
	volatile int counter = 10;
	public static void main(String[] args) {
		VolatileKeywordExample vke = new VolatileKeywordExample();
		Thread t1 = new Thread(() -> {
			while(vke.counter > 0) {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(vke.counter + " t1 ");
				vke.counter--;
			}
		});
		Thread t2 = new Thread(() -> {
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			while(vke.counter > 0) {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(vke.counter + " t2 ");
				vke.counter--;
			}
		});
		t1.start();
		t2.start();
	}	
}
