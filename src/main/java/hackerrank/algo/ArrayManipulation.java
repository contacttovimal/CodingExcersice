package hackerrank.algo;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class ArrayManipulation {

    static long arrayManipulation(int n, int[][] queries) {
        long[] outputArry = new long[n+2];
        for(int i=0; i< queries.length ;i++){
            int a = queries[i][0];
            int b = queries[i][1];
            int k = queries[i][2];
            outputArry[a] += k;
            outputArry[b+1] -= k;
        }
        System.out.println ("*********************");
        System.out.println (Arrays.toString(outputArry));
        System.out.println ("*********************");
        long max = getMax(outputArry);
        return max;
    }

    private static long getMax(long[] inputArry){
        long max = Long.MIN_VALUE;
        long sum=0;
        for(int i=0; i<inputArry.length; i++){
            sum += inputArry[i];
            max = Math.max(sum,max);
        }
        return max;
    }
    // Complete the arrayManipulation function below.
   /* static long arrayManipulation(int n, int[][] queries) {
        long result = Integer.MIN_VALUE;
        long arry[] = new long[n];
        for (int j = 0, k= queries.length-1; j < k; j++,k--) {
            if (arry.length >= queries[j][0] && arry.length >= queries[j][1] && queries[j][2] != 0) {
                for (int i = queries[j][0] - 1; i <= queries[j][1] - 1; i++) {
                    arry[i] += queries[j][2];
                    if (result < arry[i]) {
                        result = arry[i];
                    }
                }
            }
            if (arry.length >= queries[k][0] && arry.length >= queries[k][1] && queries[k][2] != 0) {
                for (int i = queries[k][0] - 1; i <= queries[k][1] - 1; i++) {
                    arry[i] += queries[k][2];
                    if (result < arry[i]) {
                        result = arry[i];
                    }
                }
            }
        }
        if(queries.length%2!=0){
            for (int i = queries[(queries.length/2)][0] - 1; i <= queries[(queries.length/2)][1] - 1; i++) {
                arry[i] += queries[(queries.length/2)][2];
                if (result < arry[i]) {
                    result = arry[i];
                }
            }
        }
        return result;
    }*/

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("D:\\Backup_C_D\\code\\TestDemo\\src\\hackerrank\\dummy.txt"));

        String[] nm = scanner.nextLine().split(" ");

        int n = Integer.parseInt(nm[0]);

        int m = Integer.parseInt(nm[1]);

        int[][] queries = new int[m][3];

        for (int i = 0; i < m; i++) {
            String[] queriesRowItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int j = 0; j < 3; j++) {
                int queriesItem = Integer.parseInt(queriesRowItems[j]);
                queries[i][j] = queriesItem;
            }
        }

        long result = arrayManipulation(n, queries);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
