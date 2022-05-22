package careercup;

import java.util.*;

class Piano {
	public void play() {
		System.out.println("plaing piano");
	}
}

interface instrument {
}

interface Inst1 extends instrument {
	public void play();

}

interface Inst2 extends instrument {
	public void tune();

}

class Drum implements Inst2 {
	public void tune() {
		System.out.println("Drumming ");
	}
}

class commonInstrument extends Piano implements instrument {
	public void play() {
		super.play();
	}
}

class VersionData implements Comparable<VersionData>{
	String version;
	public VersionData(String version){
		this.version=version;
	}
	public int compareTo(VersionData o) {
		if(this.version.equals(o.version)){
			return 0;
		}
		VersionData comp =this.version.length() > o.version.length() ? o : this;
		int length = comp.version.length();
		int diff=0;
		for(int k=0; k<length; k++){
			if(o.version.charAt(k)== this.version.charAt(k)){
				continue;
			}
			diff =  this.version.charAt(k) - o.version.charAt(k);
			break;
		}
		if(diff==0){
			if(this != comp){
				diff = this.version.charAt(length-1);
			}else{
				diff = comp.version.charAt(length-1);
			}
		}
		return diff;
	}		
	 @Override
	public String toString() {
		return version;
	}
	
}

public class VersionCompare {

	public static void main(String s[]) {
		 ArrayList<VersionData> versionList = new ArrayList<VersionData>();
		 versionList.add(new VersionData("3.1.0.1"));
		 versionList.add(new VersionData("1.0.1"));
		 versionList.add(new VersionData("0.1"));
		 versionList.add(new VersionData("1.1"));
		 versionList.add(new VersionData("4.1"));
		 versionList.add(new VersionData("5.0.1.1"));
		 versionList.add(new VersionData("5.0.1"));
		 versionList.add(new VersionData("5.0.0.1"));
		 versionList.add(new VersionData("1.2.0.1"));
		 versionList.add(new VersionData("4.1.0.1"));
		 
		 Collections.sort(versionList);
		 System.out.println(versionList);
		 
		 TreeSet<Integer> treeSet = new TreeSet<Integer>();
		 HashSet<Integer> hSet = new HashSet<Integer>();
		 treeSet.add(1);
		 hSet.add(1);
		 treeSet.add(2);
		 hSet.add(2);
		 treeSet.add(1);
		 hSet.add(1);
		 Iterator it = treeSet.iterator();
		 while(it.hasNext()){
			 System.out.println(it.next());
		 }
		 
	}
}
