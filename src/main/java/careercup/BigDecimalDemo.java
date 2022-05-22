package careercup;

import java.io.IOException;
import java.math.BigDecimal;

public class BigDecimalDemo {

    public static void main(String args[]) throws IOException {
      
    	BigDecimalDemo bdm= null;
    	bdm.test1();
    	
      //floating point calculation
      double amount1 = 2.15;
      double amount2 = 1.10;
      System.out.println("difference between 2.15 and 1.0 using double is: " + (amount1 - amount2));
    
      
      BigDecimal amount3 = new BigDecimal(2.15);
      BigDecimal amount4 = new BigDecimal(1.10) ;
      System.out.println("difference between 2.15 and 1.0 using BigDecmial is: " + (amount3.subtract(amount4)));

      
      //Use BigDecimal for financial calculation
      BigDecimal amount5 = new BigDecimal("2.15");
      BigDecimal amount6 = new BigDecimal("1.10") ;
      System.out.println("difference between 2.15 and 1.0 using BigDecimal is: " + (amount5.subtract(amount6)));      
    }     
    public static void test1(){
    	System.out.println("test called");
    }

}
