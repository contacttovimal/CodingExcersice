package collection;

import java.util.Iterator;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

class MyInteger{
	int i;
	public MyInteger(int i){
		this.i = i;
	}
	@Override
	public int hashCode() {
		return 1;
	}
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		MyInteger inObj =(MyInteger)obj;
		return (this.i==inObj.i);
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return String.valueOf(i);
	}

}

public class ConcurrencyLevelTesting {
	public static void main(String[] args) {
		try {
			ConcurrentHashMap<MyInteger, String> concurrMap = new ConcurrentHashMap<MyInteger, String>(
					1, 0.9F, 1);
			MapWriter writer1 = new MapWriter("W1", concurrMap, 20);
			MapWriter writer2 = new MapWriter("W2", concurrMap, 15);
			MapWriter writer3 = new MapWriter("W3", concurrMap, 20);
			Thread writherThread1 = new Thread(writer1);
			MapReader reader1 = new MapReader("R1", concurrMap, 30);
			MapReader reader2 = new MapReader("R2", concurrMap, 20);
			Thread writherThread2 = new Thread(writer2);
			Thread writherThread3 = new Thread(writer3);
			Thread readerThread1 = new Thread(reader1);
			Thread readerThread2 = new Thread(reader2);
			writherThread1.start();
			writherThread2.start();
//			writherThread3.start();
			readerThread1.start();
			readerThread2.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

class MapReader implements Runnable {
	ConcurrentHashMap<MyInteger, String> conMap = null;
	long waitTime;
	String theadName = null;

	public MapReader(String threadName,
			ConcurrentHashMap<MyInteger, String> conMap, int waittime) {
		this.conMap = conMap;
		this.waitTime = waittime;
		this.theadName = threadName;
	}

	@Override
	public void run() {
		try {
			if("R2".equals(this.theadName)){
				int i=0;
				while(i < 50){
					int size=this.conMap.size();
					while(size-- > 0){
						System.out.println(this.theadName + " GET "+i+" : " +size+" : "+ this.conMap.containsValue(String.valueOf(size -1)));
					}
					i++;
					Thread.sleep(this.waitTime);
				}
			}else{
				int i=0; 
				while(i++ < 50){
					Iterator<Entry<MyInteger, String>> itr = this.conMap.entrySet()
							.iterator();
					while (itr.hasNext()) {
						System.out.println(this.theadName + " : " + itr.next());
						Thread.currentThread().sleep(2);
					}
					Thread.sleep(this.waitTime);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}

class MapWriter implements Runnable {
	ConcurrentHashMap<MyInteger, String> conMap = null;
	long waitTime;
	String theadName = null;

	public MapWriter(String threadName,
			ConcurrentHashMap<MyInteger, String> conMap, int waittime) {
		this.conMap = conMap;
		this.waitTime = waittime;
		this.theadName = threadName;
	}

	@Override
	public void run() {
		try {
			for (int i = 0; i < 50; i++) {
				if(this.theadName.equals("W1")){
					int size= this.conMap.size();
					this.conMap.put(new MyInteger(i), String.valueOf(i));
					System.out.println(this.theadName +" : "+ (i)+ " : WRITE : " + (i) + " : Size : " + size);
					if(i >  2){
						this.conMap.put(new MyInteger(size-2), String.valueOf(this.conMap.get((new MyInteger(size-2)))+0.5));
						System.out.println(this.theadName +" update : "+ (size-2)+ " : WRITE : " + (this.conMap.get((new MyInteger(size-2)))));
					}
					Thread.currentThread().sleep(this.waitTime);
				}else{
					this.conMap.put(new MyInteger(i), String.valueOf(i));
					System.out.println(this.theadName +" : "+ (i)+ " : WRITE : " + (i));
					/*if(i%10==0 && i > 2){
						this.conMap.put(new MyInteger(i-1), String.valueOf(this.conMap.get((new MyInteger(i)))+0.5));
						System.out.println(this.theadName +" update : "+ (i-1)+ " : WRITE : " + (this.conMap.get((new MyInteger(i-1)))));
					}*/
					Thread.currentThread().sleep(this.waitTime);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}