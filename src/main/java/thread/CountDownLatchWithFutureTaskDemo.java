package thread;

import java.util.concurrent.*;
import java.util.logging.Level;
import java.util.logging.Logger;


public class CountDownLatchWithFutureTaskDemo {

	public static void main(String args[]) {
		try {
			// FutureTask<String> cacheService = new FutureTask<String>(new
			// TaskService("CacheService", 1000));
			TaskService cacheService = new TaskService("CacheService", 1000);
			TaskService alertService = new TaskService("alertService", 1000);
			TaskService validationService = new TaskService(
					"validationService", 1000);

			ExecutorService executorService = Executors.newFixedThreadPool(2);
			executorService.awaitTermination(20, TimeUnit.SECONDS);

			LinkedBlockingDeque<Future<String>> linkedBlockingQueue = new LinkedBlockingDeque<Future<String>>();
			ExecutorCompletionService<String> completionService = new ExecutorCompletionService<String>(
					executorService, linkedBlockingQueue);

			completionService.submit(cacheService);
			completionService.submit(alertService);
			completionService.submit(validationService);

			Future<String> futureReturn = completionService.poll();
			while (futureReturn != null) {
				System.out.println(futureReturn.get(1000,TimeUnit.MILLISECONDS));
				futureReturn =completionService.poll();
			}
			System.out
					.println("All services are up, Application is starting now");
			executorService.shutdown();
		} catch (Exception ie) {
			ie.printStackTrace();
		}

	}

}

/**
 * Service class which will be executed by Thread using CountDownLatch
 * synchronizer.
 */
class TaskService implements Callable<String> {
	private final String name;
	private final int timeToStart;
	private String data;

	public TaskService(String name, int timeToStart) {
		this.name = name;
		this.timeToStart = timeToStart;
	}

	@Override
	public String call() throws Exception {
		try {
			Thread.sleep(timeToStart);
			data = name + " - " + System.currentTimeMillis();
			System.out.println(data + " is Up");
		} catch (InterruptedException ex) {
			Logger.getLogger(Service.class.getName()).log(Level.SEVERE, null,
					ex);
		}
		return data;
	}

}
