package collection;

import java.util.Iterator;
import java.util.WeakHashMap;

public class WeakHashMapDemo {
	public static void main(String[] args) {
		WeakHashMap<Human, String> wkMap = new WeakHashMap<Human, String>();
		Human h1 = new Human("Human1");
		Human h2 = new Human("Human2");
		Human h3 = new Human("Human3");
		wkMap.put(h1, "H1");
		wkMap.put(h2, "H2");
		wkMap.put(h3, "H3");
		Iterator<Human> it = wkMap.keySet().iterator();
		while (it.hasNext()){
			System.out.println(it.next());
		}
		h1 = null;
		System.gc();
		it = wkMap.keySet().iterator();
		while (it.hasNext()){
			System.out.println(it.next());
		}

	}
}

class Human {
	private String name;

	public Human(String name) {
		this.name = name;
	}

	public String toString() {
		// TODO Auto-generated method stub
		return this.name;
	}
}
