package careercup;

public class JavaStringShift {

	public static void main(String[] args) {
		
		String str = "abcde";

		System.out.println("Input : " + str);
		str = strShift(str);
		System.out.println("Output : " + str);

	}


	public static String strShift(String str) {
		char[] chars=	str.toCharArray();
		char lastChar = chars[str.length()-1];
		for(int i=chars.length-1;i> 0; i--){
			chars[i]=chars[i-1];
		}
		chars[0]=lastChar;
		return new String(chars);
	}
	/*public static String strShift(String str) {
		char[] chars = new char[str.length()];
		str.getChars(str.length() - 1, str.length(), chars, 0);
		str.getChars(0, str.length() - 1, chars, 1);
		return new String(chars);
	}*/
}

class A {
	void print() {
	}
}

class B extends A {
	void print() {
	}

}
