package thread;

import java.util.concurrent.Exchanger;
import java.util.concurrent.TimeUnit;

public class ExchangerExample {  
 
   public static void main(String[] args) {
 
      Exchanger<String> exchanger = new Exchanger<String>();
 
      Thread t1 = new MyThread(exchanger, "I like coffee.");
      Thread t2 = new MyThread(exchanger, "I like tea");
      t1.start();
      t2.start();
   }
}

class MyThread extends Thread {
 
   Exchanger<String> exchanger;
   String message;
 
   MyThread(Exchanger<String> exchanger, String message) {
      this.exchanger = exchanger;
      this.message = message;
   }
 
   public void run() {
      try {
         System.out.println(this.getName() + " message: " + message);
 
         // exchange messages
         if(this.getName().equals("Thread-0")){
        	 synchronized (this.message ) {
        		 this.message.wait(); 
			}
        	 message = exchanger.exchange(message);
         }else{
        	 message = exchanger.exchange(message,100,TimeUnit.MILLISECONDS);
         }
 
         System.out.println(this.getName() + " message: " + message);
      } catch (Exception e) {
      }
   }
}