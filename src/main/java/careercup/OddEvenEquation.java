package careercup;

import java.util.Scanner;

class Base {
}

public class OddEvenEquation extends Base {
	/*  public static void main(String[] args){
		 Integer i = 100;
		 System.out.println(Integer.toBinaryString(i));
		 String str1 = (Integer.toBinaryString(i));
		 int len=0;
		 StringBuilder str2 =new StringBuilder("");
		 while(len < str1.length()){
			 if(str1.charAt(len)=='0'){
				 str2.append("1");
			 }else{
				 str2.append("0");
			 }
			 len++;
		 }
		 Integer i1 = Integer.parseInt(str2.toString(), 2);
		 System.out.println(i1);
      }*/
	  public static void main(String[] args){
		  
		  Scanner scr = new Scanner(System.in);
		  int count = Integer.parseInt(scr.nextLine());
		  String str= scr.nextLine();
		  String str1[] = str.split(" ");
		  System.out.println(str1);
		  int arry[] = new int[str1.length];
		  int arry_odd[] = new int[str1.length];
		  int arry_even[] = new int[str1.length];
		  int k=0;
		  int odd_k=0;
		  int even_k=0;
		  while(k < str1.length){
			  arry[k]= Integer.parseInt(str1[k]);
			  if(arry[k]%2==0){
				  arry_even[even_k++]=arry[k];
			  }else{
				  arry_odd[odd_k++]=arry[k];
			  }
			  k++;
		  }
		  int oddCount=0;
		  int evenCount=0;
		  int sum_odd=0;
		  int sum_even=0;
		  while(oddCount < arry_odd.length){
			  if(arry_odd[oddCount]==0){
				  oddCount++;
				  continue;
			  }
			  if(oddCount==0){
				  sum_odd = arry_odd[oddCount];
				  oddCount++;
				  continue;
			  }
			  if(oddCount%2==0){
				  sum_odd += arry_odd[oddCount];
			  }else{
				  sum_odd *= arry_odd[oddCount];
			  }
			  oddCount++;
		  }
		  while(evenCount < arry_even.length){
			  if(arry_even[evenCount]==0){
				  evenCount++;
				  continue;
			  }
			  if(evenCount == 0){
				  sum_even = arry_even[evenCount];
				  evenCount++;
				  continue;
			  }
			  if( evenCount%2==0){
				  sum_even += arry_even[evenCount];
			  }else{
				  sum_even *= arry_even[evenCount];
			  }
			  evenCount++;
		  }
		  System.out.println(sum_odd + " - " + sum_even);
		  if(sum_even == sum_odd){
              System.out.print("NEUTRAL");
          }else if(sum_even > sum_odd){
              System.out.print("EVEN");
          }else{
              System.out.print("ODD");
          }
	  
	  }
}