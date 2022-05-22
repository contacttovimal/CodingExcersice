package thread;

import java.util.*;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Java program to demonstrate how to use CyclicBarrier in Java. CyclicBarrier
 * is a new Concurrency Utility added in Java 5 Concurrent package.
 * 
 * @author Javin Paul
 */

class Parties {
	public Parties(String key,double total){
		this.key=key;
		this.total = total;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	private String key;
	private double total;
}

public class CyclicBarrierExample {

	// Runnable task for each thread
	private static class Task implements Runnable {
		Map partiesMap = new HashMap<Parties, List<Integer>>();
		private CyclicBarrier barrier;
		Parties party;

		public Task(CyclicBarrier barrier,Map partiesMap,Parties party) {
			this.barrier = barrier;
			this.partiesMap = partiesMap;
			this.party = party;
		}

		public void run() {
			try {
				List<Integer> list = (List) partiesMap.get(party);
				 double rowTotal = 0;
				if(list != null && list.size() > 0){
						for(Integer i:list){
							rowTotal += i;
						}
				}
				party.setTotal(rowTotal);
				System.out.println(Thread.currentThread().getName()
						+ " is waiting on barrier, party : " + party.getKey()+ " total Row sum : " + rowTotal);
				barrier.await();
				System.out.println(Thread.currentThread().getName()
						+ " has crossed the barrier");
			} catch (InterruptedException ex) {
				Logger.getLogger(CyclicBarrierExample.class.getName()).log(
						Level.SEVERE, null, ex);
			} catch (BrokenBarrierException ex) {
				Logger.getLogger(CyclicBarrierExample.class.getName()).log(
						Level.SEVERE, null, ex);
			}
		}
	}
	private static class BarrierTask implements Runnable {
		HashMap<Parties, List<Integer>> partiesMap;
		public BarrierTask(HashMap partiesMap){
			this.partiesMap = partiesMap;
		}
		public void run() {
			try{
				System.out.println("All parties are arrived at barrier " );
				Thread.sleep(2000);
				// This task will be executed once all thread reaches barrier
				Iterator<Parties> partyIt = partiesMap.keySet().iterator();
				double totalSum =0;
				while(partyIt.hasNext()){
					totalSum += partyIt.next().getTotal();
				}
				System.out.println( "total sum : " + totalSum);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String args[]) {

		Parties party1 = new Parties("Party1",0);
		List<Integer> list1 = new ArrayList<Integer>();
		list1.add(1);list1.add(2);list1.add(3);

		Parties party2 = new Parties("Party2",0);
		List<Integer> list2 = new ArrayList<Integer>();
		list2.add(4);list2.add(5);list2.add(6);

		
		Parties party3 = new Parties("Party3",0);
		List<Integer> list3 = new ArrayList<Integer>();
		list3.add(7);list3.add(8);list3.add(9);

		
		HashMap<Parties , List<Integer>> partiesMap = new HashMap<Parties, List<Integer>>();
		partiesMap.put(party1, list1);
		partiesMap.put(party2, list2);
		partiesMap.put(party3, list3);
		
		// creating CyclicBarrier with 3 parties i.e. 3 Threads needs to call
		// await()
		final CyclicBarrier cb = new CyclicBarrier(partiesMap.size(), new BarrierTask(partiesMap));

		// starting each of thread
		Thread t1 = new Thread(new Task(cb,partiesMap,party1), "Thread 1");
		Thread t2 = new Thread(new Task(cb,partiesMap,party2), "Thread 2");
		Thread t3 = new Thread(new Task(cb,partiesMap,party3), "Thread 3");
		t1.start();
		t2.start();
		t3.start();

	}
}
