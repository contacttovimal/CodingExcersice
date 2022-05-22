package collection;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.TreeSet;

public class HashSetDemo {
	public static void main(String[] args) {
		Person1 p1 = new Person1("TEST");
		Person1 p2 = new Person1("TEST1");
		ArrayList<Person1> personList = new ArrayList<Person1>();
		personList.add(p2);
		personList.add(p1);
		for (Iterator iterator = personList.iterator(); iterator.hasNext();) {
			Person1 person = (Person1) iterator.next();
			System.out.println(person.name);
		}   
		
		TreeSet<String> tr;
		HashSet<Pets> hset = new HashSet<Pets>();
//		System.out.println(hset.add("ONE"));
//		System.out.println(hset.add("ONE"));
		/*for (Iterator iterator = hset.iterator(); iterator.hasNext();) {
			Person person = (Person) iterator.next();
//			System.out.println(person);
			
		}*/
		hset.add(Pets.DOG);
		hset.add(Pets.CAT);
		System.out.println(hset.contains(Pets.DOG));
	}
	enum Pets {DOG,CAT,RAT}
}

class Person1 implements Comparable<Person1>,Cloneable{
	public String name;
	/*public boolean equals(Object obj) {
		Person p = (Person)obj;
		return this.name.equals(p.name);
	}*/
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return name.length();
	}
	public Person1(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return name;
	}
	public int compareTo(Person1 o) {
		// TODO Auto-generated method stub
		return 2;
	}
	@Override
	protected Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
	}
}