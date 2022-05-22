package collection;

import java.util.*;

class comp implements Comparator<Set>{
	public int compare(Set set1, Set set2) {
		// TODO Auto-generated method stub
		if(set1 == null || set2 == null  || !(set1 instanceof Set) || !(set2 instanceof Set)){
			return 0;
		}
		return (set2.size() - set1.size());
	}
}

public class GenerateCargoSet {
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		List<Set> setList = new ArrayList<Set>();
		Set<Integer> s1 = new HashSet();
		s1.add(1);s1.add(2);
		Set<Integer> s2 = new HashSet();
		s2.add(1);s2.add(2);s2.add(3);
		Set<Integer> s3 = new HashSet();
		s3.add(1);s3.add(2);s3.add(3);s3.add(4);
		setList.add(s1);
		setList.add(s2);
		setList.add(s3);
		Collections.sort(setList,   new comp());
		
		List<Integer> cargoSet = new ArrayList<Integer>();
		cargoSet.add(1);cargoSet.add(5);cargoSet.add(6);cargoSet.add(3);
		cargoSet.add(8);cargoSet.add(4);cargoSet.add(8);cargoSet.add(1);
		cargoSet.add(9);cargoSet.add(11);cargoSet.add(15);cargoSet.add(18);
		cargoSet.add(1);cargoSet.add(1);cargoSet.add(3);cargoSet.add(3);cargoSet.add(5);cargoSet.add(7);
		int stdCargoSize = 8;
		int filteredCargo =0;
		int filteredIndipenentCargo =0;
		HashMap<String, List> filteredCargoGroup = new HashMap<String, List>();
		List filteredList = new ArrayList<Integer>();
		for (Iterator<Integer> iterator1 = cargoSet.listIterator(); iterator1.hasNext();) {
			int tempCargoSize = stdCargoSize;
			for (Iterator iterator = cargoSet.listIterator(); iterator.hasNext();) {
				Integer cargoSize = (Integer) iterator.next();
				if(cargoSize >= stdCargoSize){
					List crLst = new ArrayList<Integer>();
					crLst.add(cargoSize);
					filteredCargoGroup.put("OVERSIZECARGO-"+(++filteredIndipenentCargo), crLst);
					iterator.remove();
				}else {
					if(tempCargoSize-cargoSize>=0){
						tempCargoSize=tempCargoSize-cargoSize;
						List crLst = filteredCargoGroup.get("CARGO-"+filteredCargo);
						if(crLst==null){
							crLst=new ArrayList<Integer>();	
						}
						crLst.add(cargoSize);
						filteredCargoGroup.put("CARGO-"+filteredCargo, crLst);
						iterator.remove();
					}else{
						continue;
					}
				}
			}
			filteredCargo++;
		}
		
		Iterator<String> mapIterator = filteredCargoGroup.keySet().iterator();
		while(mapIterator.hasNext()){
			String key = mapIterator.next();
			System.out.println(key  + "-" + filteredCargoGroup.get(key));
		}
		
		
		
	}
}
