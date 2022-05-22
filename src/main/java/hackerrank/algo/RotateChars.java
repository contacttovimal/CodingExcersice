package hackerrank.algo;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class RotateChars {
    static String lowerCase = "abcdefghijklmnopqrstuvwxyz";
    static String upperCase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    // Complete the caesarCipher function below.
    static String caesarCipher(String s, int k) {
        k = k%26;
        StringBuilder stb = new StringBuilder();
        for(int i=0; i < s.length(); i++){
           if( lowerCase.indexOf(s.charAt(i)) != -1){
               checkStr(lowerCase,s,i,stb,k);
           } else if(upperCase.indexOf(s.charAt(i)) != -1){
               checkStr(upperCase,s,i,stb,k);
            }else{
                stb.append(s.charAt(i));
            }
        }
        return stb.toString();
    }


    public  static void checkStr(String original, String s, int i, StringBuilder stb, int k) {
        int idx = original.indexOf(s.charAt(i));
        if (idx != -1) {
            idx += k;
            if (idx > 25) {
                idx = idx - 26;
            }
            stb.append(original.charAt(idx));
        }
    }
    
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("D:\\Backup_C_D\\code\\TestDemo\\src\\hackerrank\\dummy.txt"));

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        String s = scanner.nextLine();

        int k = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        String result = caesarCipher(s, k);

        System.out.println(result);
        bufferedWriter.write(result);
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
