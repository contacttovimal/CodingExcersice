package careercup;

public class SubStringFinder {
	public static void main(String[] args) {
		String a="abcmnfdegkhijl",b="cfgk",c="gkl";
		String subStr="";
		int minIndex=Integer.MAX_VALUE,maxIndex=Integer.MIN_VALUE;
		for(int k=0; k < b.length();k++){
			int b_indx =a.indexOf(b.charAt(k));
			if(b_indx != -1){
				if(b_indx  < minIndex ){
					minIndex= b_indx;
				}
				if(b_indx  > maxIndex  ){
					maxIndex = b_indx;
				}
			}
		}
		if(maxIndex != Integer.MAX_VALUE && minIndex != Integer.MIN_VALUE){
			subStr = a.substring(minIndex,maxIndex);
		}
		
		minIndex=Integer.MAX_VALUE;maxIndex=Integer.MIN_VALUE;
		for(int k=0; k<c.length();k++){
			int c_indx = subStr.indexOf(c.charAt(k));
			if(c_indx != -1){
				if(c_indx < minIndex ){
					minIndex = c_indx;
				}
				if(c_indx > maxIndex ){
					maxIndex = c_indx;
				}
			}
		}
		subStr =subStr.substring(0,minIndex);
		if(subStr.length()==0){
			subStr=subStr.substring(maxIndex);
		}
		System.out.println(subStr);
		
	}
}
