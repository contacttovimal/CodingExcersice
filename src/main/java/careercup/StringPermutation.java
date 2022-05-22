package careercup;

public class StringPermutation {
	public static void main(String[] args) {
		arrangeLettersToWordNoRepeat("ABC".toCharArray());
	}
	
	public static void arrangeLettersToWordNoRepeat(char[] letters) {
		int n = (int) Math.pow(2, letters.length);
		for (int number = n - 1; number >= 0; number--) {
			if (number == 0) {
				System.out.print("empty string");
				break;
			}
			for (int position = letters.length - 1; position >= 0; position--) {
				if ((number & (1 << position)) != 0) {
					System.out.print(" , "+letters[letters.length - 1 - position]);
				}
			}
			System.out.println();
		}
		for(int k=7;k>=0;k--){
			for(int j=2;j>=0;j--){
				System.out.println(" ***************** ");
				System.out.println(k + " & " + j + " : " + (k & j));
				System.out.println(k + " & " + (1 << j) + " : " + (k & (1 << j)));
				System.out.println(" ***************** ");
			}
		}
	}
}
