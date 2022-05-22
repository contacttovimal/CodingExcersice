package careercup;

public class OddEvenSwap {
	public static void main(String[] args) {
		int[] num = new int[]{1,2,3,4,5,6,7,8};
		odd_even_sort(num, num.length);
	}
	static void swap(int num[], int a, int b) {
		int tmp = num[a];
		num[a] = num[b];
		num[b] = tmp;
	}
	static void odd_even_sort(int[] num, int n) {
		int swap_cnt = 0;
		int k = 0;
		for (int i = 0; i < n; i++) {
			if (num[i] % 2 == 1) {
				swap(num, num[i], num[k]);
				for (int j = i; j > k + 1; j--)
					swap(num, num[j], num[j - 1]);
				k++;
			}
		}
		for (int i = 0; i < num.length; i++) {
			System.out.println(num[i] + " , ");
		}
	}
}
