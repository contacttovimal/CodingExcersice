package thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class FutureTaskExample {

	public static void main(String[] args) {
	try{
		// Cancelling code before run
//		runExecutorService();
		runCompService();
//		checkException();
		
//		  boolean b = future.cancel(true);
//		  System.out.println("Cancelled="+b);
//		future.run();
		// Cancelling code after run
		
/*		  boolean b = future.cancel(true);
		  System.out.println("Cancelled="+b);
*/
	}catch(Exception e){
		e.printStackTrace();
	}
	}
	public static void runCompService(){
		CallableTask task1 = new CallableTask("TASK1");
		CallableTask task2 = new CallableTask("TASK2");
		CallableTask task3 = new CallableTask("TASK3");
		CallableTask task4 = new CallableTask("TASK4");
		CallableTask task5 = new CallableTask("TASK5");
		CallableTask task6 = new CallableTask("TASK6");

		ExecutorService executorService = Executors.newFixedThreadPool(2);
		ExecutorCompletionService exeCompService = new ExecutorCompletionService(executorService);

		List<Future<String>> futureTaskList = new ArrayList<Future<String>>();
		futureTaskList.add(exeCompService.submit(task1));
		futureTaskList.add(exeCompService.submit(task2));
		futureTaskList.add(exeCompService.submit(task3));
		futureTaskList.add(exeCompService.submit(task4));
		futureTaskList.add(exeCompService.submit(task5));
		futureTaskList.add(exeCompService.submit(task6));
		  try {
			  for(Future<String> future : futureTaskList){
				String result = future.get(10,TimeUnit.MILLISECONDS);
				future.cancel(true);
				System.out.println("Result=" + result);
			  }
			} catch (Exception e) {
				System.out.println("EXCEPTION!!!");
				e.printStackTrace();
			}finally{
			}
			executorService .shutdown();
	}
	public static void runExecutorService() throws Exception{
		CallableTask task1 = new CallableTask("TASK1");
		CallableTask task2 = new CallableTask("TASK2");
		CallableTask task3 = new CallableTask("TASK3");
		CallableTask task4 = new CallableTask("TASK4");
		CallableTask task5 = new CallableTask("TASK5");
		CallableTask task6 = new CallableTask("TASK6");

		List<CallableTask> taskList = new ArrayList<CallableTask>();
		taskList.add(task1);
		taskList.add(task2);
		taskList.add(task3);
		taskList.add(task4);
		taskList.add(task5);
		taskList.add(task6);

		ExecutorService executorService = Executors.newFixedThreadPool(2);
		List<Future<String>> futureTaskList = executorService.invokeAll(taskList);
		
		for(Future<String> f : futureTaskList){
			String str = f.get(1,TimeUnit.MILLISECONDS);
			System.out.println(str);
		}
		executorService.shutdown();
	}
	public static void checkException(){
		try{
		CallableTask task7 =new CallableTask("TASK7");
		ExecutorService executorService = Executors.newFixedThreadPool(2);
		ExecutorCompletionService exeCompService = new ExecutorCompletionService(executorService);
		
		Future<String> retFut= exeCompService.submit(task7);
		System.out.println(retFut.get());
		System.out.println("*******************");
//		executorService.execute(task7);
		executorService.shutdown();
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
}


class CallableTask implements Callable<String>,Runnable {
	private String name;
	public CallableTask(String name){
		this.name = name;
	}
	public String call() throws Exception {
//		System.out.println("Executing call() !!!" + this.name);
//		Thread.sleep(100);
		  /*if(1==1)
		  throw new java.lang.Exception("Thrown from call()");
*/
		if(name.equalsIgnoreCase("TASK1") || name.equalsIgnoreCase("TASK2") || name.equalsIgnoreCase("TASK3")){
//			System.out.println("Sleep For " + this.name);
			Thread.sleep(5);
		}
		if(true){
			throw new Exception("Thrown exception");
		}
		  return "success-"+this.name;
	}
	public void run() {
		try{
//		System.out.println("Executing call() !!!" + this.name);
//		Thread.sleep(100);
		  /*if(1==1)
		  throw new java.lang.Exception("Thrown from call()");
*/
		if(name.equalsIgnoreCase("TASK1") || name.equalsIgnoreCase("TASK2") || name.equalsIgnoreCase("TASK3")){
//			System.out.println("Sleep For " + this.name);
			Thread.sleep(5);
		}
		if(true){
			throw new Exception("Thrown exception");
		}
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
