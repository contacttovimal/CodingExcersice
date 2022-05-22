package careercup;

public class PowDemo {
	public static void main(String[] args) {
		// getFiboNum(0, 1, 20);
		System.out.println(getPow(4, 2, 0));
//		findMissingNum();
	}
	public static void findMissingNum(){
		int arr[]= new int[] {1,2,2,3,5 };
		int n = 5;
		int sum=0;
		int sum1= 0;
		int a = (n*(n+1))/2;
		int b = (n*(n+1)*((2*n)+1))/6;
		for(int i= 0; i < n;i++){
			sum = sum + arr[i];
			sum1 = sum1 + (arr[i]*arr[i]);
			
		}	
		int x = ((a-sum)+((b-sum1)/(a-sum)))/ 2;
		System.out.println("missing  :"+x);
		System.out.println("dup : " + ((x-(a-sum))));
	}

	public static void getFiboNum(int a, int b, int n) {
		int sum = a + b;
		if (sum > n) {
			return;
		}
		System.out.println(sum + ",");
		getFiboNum(b, sum, n);
	}

	public static int getPow(int x, int n, int level) {
		int sum = 1;
		if (n > 0 ) {
			sum = x * getPow(x, n - 1, level+1);
		}
		if(level ==0){
			System.out.println(" sum : " + sum);
			if (n <= 0) {
				return sum;
			} else {
				sum += getPow(x, n - 1, 0);
			}
		}
		return sum;
	}
}
