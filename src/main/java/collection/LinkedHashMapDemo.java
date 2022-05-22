package collection;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.Set;

public class LinkedHashMapDemo{
	private String name;
	public String toString() {
		return this.name;
	}
	public LinkedHashMapDemo(String name){
		this.name = name;
	}
	private String getName(){
		return this.name;
	}
	public static void main(String[] args) {
		HashMap<LinkedHashMapDemo, String> hMap =new HashMap<LinkedHashMapDemo, String>();
		
//		HashMap<LinkedHashMapDemo,String> s = new HashMap<LinkedHashMapDemo, String>();
		/*LinkedHashMap<LinkedHashMapDemo,String> linkedMap = new LinkedHashMap<LinkedHashMapDemo, String>(){
			 protected boolean removeEldestEntry(Map.Entry eldest) {
			             return size() > 3;
			          };
		};*/
		LinkedHashMap<LinkedHashMapDemo,String> linkedMap = new LinkedHashMap<LinkedHashMapDemo, String>(3,1,true){
				protected boolean removeEldestEntry(
							Entry<LinkedHashMapDemo, String> eldest) {
					if(this.size() > 5){
						return true;	
					}
					return false;
				}
		};
		LinkedHashMapDemo hd1 = new LinkedHashMapDemo("One");
		LinkedHashMapDemo hd2 = new LinkedHashMapDemo("Two");
		LinkedHashMapDemo hd3 = new LinkedHashMapDemo("Three");
		LinkedHashMapDemo hd4 = new LinkedHashMapDemo("Four");
		LinkedHashMapDemo hd5 = new LinkedHashMapDemo("Five");
/*		s.put(hd1, "One");
		s.put(hd2, "Two");
		s.put(hd3, "Three");
*/		
		linkedMap.put(hd1, "One");
		linkedMap.put(hd2, "Two");
		linkedMap.put(hd3, "Three");
		linkedMap.put(hd4, "Four");
		
		hMap.put(hd1, "One");
		hMap.put(hd2, "Two");
		hMap.put(hd3, "Three");
		hMap.put(hd4, "Four");

		/*		Set<Entry<LinkedHashMapDemo, String>> entrySet = s.entrySet();
		for (Entry entry : entrySet) {
			System.out.println(((LinkedHashMapDemo)entry.getKey()).getName());
		}
*/		
		Set<Entry<LinkedHashMapDemo, String>> entrySet = linkedMap.entrySet();
		for (Entry entry : entrySet) {
			System.out.println(((LinkedHashMapDemo)entry.getKey()).getName());
		}
		System.out.println("-------------------------------------");
		entrySet = hMap.entrySet();
		for (Entry entry : entrySet) {
			System.out.println(((LinkedHashMapDemo)entry.getKey()).getName());
		}
		System.out.println("-------------------------------------");
		System.out.println("fetch : " +linkedMap.get(hd1));
		linkedMap.put(hd5, "Five");
		 entrySet = linkedMap.entrySet();
		for (Entry entry : entrySet) {
			System.out.println(((LinkedHashMapDemo)entry.getKey()).getName());
		}
		
	}
}
