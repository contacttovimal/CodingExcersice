package hackerrank;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class CompareSubString {

    static long substrCount(int n, String s) {
        int result=n;
        int start=0 ,end=0;


        for(int i=0; i< n; i++){
            start = i-1; end=i+1;
            while(start >=0 && end < n){
                if(s.charAt(start) == s.charAt(end) && s.charAt(start)==s.charAt(i-1)){
                    result++;
                    start--; end++;
                }else{
                    break;
                }
            }

            int k=1;
            while((i+k) < n && s.charAt(i+k) == s.charAt(i)) { k++; }
            result += k/2;
        }
        return result;
    }

    // Complete the substrCount function below.
   /* static long substrCount(int n, String s) {
        int result=0;
        int length=0;
        boolean flag = true;

        for(int i=0; i<s.length(); i++){
            length++;
            for(int j=0; (j+length) <= s.length() && j<s.length(); j++){
                flag = true;
                    String substr = s.substring(j, j + length);
                    Character prevChar = null;
                    for (int k = 0, l = substr.length() - 1; k < l && k < substr.length(); k++, l--) {
                        if (substr.charAt(k) != substr.charAt(l) || (prevChar != null && prevChar != substr.charAt(l))) {
                            flag = false;
                            break;
                        }else{
                            if(prevChar == null) {
                                prevChar = substr.charAt(k);
                            }
                        }
                    }
                    if (flag) {
                        result++;
                    }
            }
        }
        return result;
    }*/

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("test.txt"));

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        String s = scanner.nextLine();

        long result = substrCount(n, s);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
