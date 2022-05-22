package hackerrank.algo;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ArrayManipulation_mine {

    static long arrayManipulation(int n, int[][] queries) {
        long result = Integer.MIN_VALUE;
        long arry[] = new long[n];
        for (int i = 0, k = arry.length - 1; i < k; i++, k--) {
            for (int j = 0; j < queries.length; j++) {
                if (i >= queries[j][0] - 1 && i <= queries[j][1] - 1 && queries[j][2] != 0) {
                    arry[i] += queries[j][2];
                    if (result < arry[i]) {
                        result = arry[i];
                    }
                }
                if (k >= queries[j][0] - 1 && k <= queries[j][1] - 1 && queries[j][2] != 0) {
                    arry[k] += queries[j][2];
                    if (result < arry[k]) {
                        result = arry[k];
                    }
                }
            }
        }
        return result;
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

        long arry[] = new long[n+2];

        for (int i = 0; i < m; i++) {
            int start = scanner.nextInt();
            int end = scanner.nextInt();
            int value = scanner.nextInt();
            arry[start] += value;
            arry[end+1] -= value;
        }

        long result = Long.MIN_VALUE;
        long sum =0;
        for(int k=0; k< arry.length; k++){
            sum += arry[k];
            result = Math.max(sum,result);
        }


        //long result = arrayManipulation(n, queries);
        //System.out.println(result);
        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }

    public static int birthdayCakeCandles(List<Integer> candles) {
        // Write your code here
        int count=0;
        int max = Integer.MIN_VALUE;
        for(int candle:candles){
            if(max < candle){
                max = candle;
            }
        }
        int finalMax = max;
        return candles.stream().filter(candle-> candle == finalMax).collect(Collectors.toList()).size();
    }
}
