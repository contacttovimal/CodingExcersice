package hackerrank.math;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class DecimalToBinaryConv {

    public static String solve(int n){
        String result = "";
        long[] longArr = new long[n+1];
        for(int i=1; i<= n ; i++){
            longArr[i] = Long.valueOf(Integer.toBinaryString(i))*9;
            if(longArr[i]%n==0){
                return String.valueOf(longArr[i]);
            }
        }
        return result;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(("D:\\Backup_C_D\\code\\TestDemo\\src\\hackerrank\\dummy.txt")));

        int t = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int tItr = 0; tItr < t; tItr++) {
            int n = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            String result = solve(n);

            bufferedWriter.write(result);
            bufferedWriter.newLine();
        }

        bufferedWriter.close();

        scanner.close();
    }
}
