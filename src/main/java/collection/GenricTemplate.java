package collection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GenricTemplate {
	public static void main(String[] args) {
		Person p1 = new Person("P1","PERSON1",10);
		Person p2 = new Person("P2","PERSON2",10);
		Person p3 = new Person("P3","PERSON3",10);
		Person p4 = new Person("P4","PERSON4",20);
		Person p5 = new Person("P5","PERSON5",20);
		Person p6 = new Person("P6","PERSON6",20);
		Person p7 = new Person("P7","PERSON7",20);
		Person p8 = new Person("P8","PERSON8",20);
		Person p9 = new Person("P9","PERSON9",30);
		Person p10 = new Person("P10","PERSON10",30);
		TemplateMap<HashMap> tmap = new TemplateMap<HashMap>();
		tmap.put(p1.persionID, p1);
		tmap.put(p2.persionID, p2);
		tmap.put(p3.persionID, p3);
		tmap.put(p4.persionID, p4);
		tmap.put(p5.persionID, p5);
		tmap.put(p6.persionID, p6);
		tmap.put(p7.persionID, p7);
		tmap.put(p8.persionID, p8);
		tmap.put(p9.persionID, p9);
		tmap.put(p10.persionID, p10);
		System.out.println(tmap.getDeptPersonCount(10));
		System.out.println(tmap.getDeptPersonCount(20));
		System.out.println(tmap.getDeptPersonCount(30));
	}
}

class TemplateMap<T extends Map> {
	private HashMap storedMap = new HashMap<String, Person>();
	private HashMap<Integer, ArrayList<Person>> departmentMap = new HashMap();

	public void put(String key, Person person) {
		storedMap.put(key, person);
		ArrayList departmentList = (ArrayList) departmentMap.get(person.dept);
		if (departmentList == null) {
			departmentList = new ArrayList<Person>();
		}
		if (!departmentList.contains(person.dept)) {
			departmentList.add(person);
		}
		departmentMap.put(new Integer(person.dept), departmentList);
	}

	public Person get(String key) {
		return (Person) storedMap.get(key);
	}

	public int getDeptPersonCount(Integer deptId) {
		return departmentMap.get(deptId).size();
	}

}

class Person {
	public Person(String persionId, String personName, int dept) {
		this.name = personName;
		this.persionID = persionId;
		this.dept = dept;
	}

	String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getDept() {
		return dept;
	}

	public void setDept(int dept) {
		this.dept = dept;
	}

	public String getPersionID() {
		return persionID;
	}

	public void setPersionID(String persionID) {
		this.persionID = persionID;
	}

	int dept;
	String persionID;
}