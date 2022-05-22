package careercup;

public class Fibonacci {
	public static void main(String[] args) {
		fib(0,1,11);
	}

	public static void fib(int a,int b,int n) {
		int sum = a+b;
		if( sum > n){
			return;
		}
		System.out.println(sum +",");
		fib(b,sum,n);
	}
}
