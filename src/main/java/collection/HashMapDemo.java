package collection;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class HashMapDemo {
	private String name;

	public HashMapDemo(String name) {
		this.name = name;
	}

	public static void main(String[] args) {
		cpyal();
	}

	public static void hashCodeTest() {
		HashMap<String, Integer> hashMap = new HashMap<String, Integer>(8);
		for (int i = 0; i < 10; i++) {
			hashMap.put(new Integer(i).toString(), new Integer(i));
		}
		System.out.println(hashMap.size());
	}

	public static void test() {
		ConcurrentHashMap<HashMapDemo, String> s = new ConcurrentHashMap<HashMapDemo, String>();
		HashMapDemo hd1 = new HashMapDemo("One");
		HashMapDemo hd2 = new HashMapDemo("two");
		HashMapDemo hd3 = new HashMapDemo("three");
		System.out.println(hd1.hashCode());
		System.out.println(hd2.hashCode());
		System.out.println(hd3.hashCode());
		s.put(hd2, "Two");
		s.put(hd3, "Three");
		Set<Entry<HashMapDemo, String>> entrySet = s.entrySet();
		for (Entry entry : entrySet) {
			System.out.println(entry.getKey());
		}

		ConcurrentHashMap<Character, Integer> conMap = new ConcurrentHashMap<Character, Integer>();
		Character c1 = new Character('c');
		Character c2 = new Character('b');
		Character c3 = new Character('a');
		Integer k = conMap.putIfAbsent(c1, 1);
		System.out.println(k);
		k = conMap.putIfAbsent(c1, 2);
		System.out.println(k);
		conMap.put(c2, 2);
		conMap.put(c3, 3);
		System.out.println("*************");
		// fib(0,1);

	}

	public static void cpyal() {
		try {
			System.out.println("strB".compareTo("str"));
			System.out.println("str".compareTo("stp"));
			CopyOnWriteArrayList<Integer> cpl = new CopyOnWriteArrayList<Integer>();
			cpl.add(new Integer(1));
			cpl.add(new Integer(2));
			childThread c1 = new childThread(cpl.iterator());
			new Thread(c1).start();
			cpl.add(new Integer(3));
			cpl.add(new Integer(4));
			cpl.add(new Integer(5));
			cpl.add(new Integer(6));
			childThread c2 = new childThread(cpl.iterator());
			new Thread(c2).start();
			Thread.currentThread().join();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static class childThread implements Runnable {
		private Iterator<Integer> it;
		
		public childThread(Iterator<Integer> it) {
			this.it = it;
		}

		public void run() {
			try {
				while (it.hasNext()) {
					System.out.println(Thread.currentThread().getName()+ " - " +it.next());
					Thread.sleep(200);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	public static void fib(int a, int b) {
		int sum = a + b;
		if (sum == 0)
			sum = 1;
		if (sum > 30) {
			return;
		}
		System.out.println(sum + " ,");
		fib(b, sum);
	}

	public static void fibanocci(int t, int d) {
		int a = t + d;
		if (a == 0) {
			a = 1;
		}/*
		 * if(a>144000){ return;}
		 */
		if (a > 30) {
			return;
		}
		System.out.println(a + ",");
		fibanocci(d, a);
	}

	interface I1 {
		public void tess();
	}

	abstract class abst1 {
		public abstract void tess();
	}

	class ttt extends abst1 implements I1 {
		public void tess() {
			// TODO Auto-generated method stub

		}
	}
}
