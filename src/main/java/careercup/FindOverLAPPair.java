package careercup;

public class FindOverLAPPair {
	public static void main(String[] args) {
		int[][] x = new int[][] { { 1, 2 }, { 3, 6 }, { 5, 7 }, { 8, 10 } };
		doItNew(x);
	}

	public static void doItNew(int[][] x) {
		int[] checker = new int[x.length];
		int counter = 0;
		boolean flag = true;
		for (int i = 0; i < x.length ; i++) {
			for (int j = 0; j < x.length; j++) {
				if (i == j) {
					continue;
				}
				if (checkOverLap(x[i], x[j])) {
					flag = false;
					break;
				}
			}
			if (flag) {
//				checker[counter++] = x[i][0];
//				checker[counter++] = x[i][1];
				checker[counter++] = 1;
			}else{
				checker[counter++] = -1;
			}
			flag = true;
		}
		for (int i = 0; i < x.length; i++) {
			if (checker[i] != -1) {
				for (int a1 : x[i]) {
					System.out.print(a1 + "\t");
				}
			}
		}
	}

	public static boolean checkOverLap(int[] a, int[] b) {
		if ((a[0] > b[0] && a[0] < b[1]) || (a[1] > b[0] && a[1] < b[1])) {
			return true;
		} else if ((a[0] < b[0] && a[0] > b[1]) || (a[1] < b[0] && a[1] > b[1])) {
			return true;
		} else {
			return false;
		}
		// return a[0] - b[1] <= 0 && a[1] - b[0] >= 0;
	}
}
