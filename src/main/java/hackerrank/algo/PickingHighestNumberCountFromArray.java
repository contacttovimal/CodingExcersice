package hackerrank.algo;


import java.io.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

//https://www.hackerrank.com/challenges/picking-numbers/problem
public class PickingHighestNumberCountFromArray {


    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("D:\\Backup_C_D\\code\\TestDemo\\src\\hackerrank\\dummy.txt"));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> a = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        int result = Result.pickingNumbers(a);

        System.out.println(result);
        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }


}

class Result {

    /*
     * Complete the 'pickingNumbers' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts INTEGER_ARRAY a as parameter.
     */

    public static int pickingNumbers(List<Integer> a) {
        // Write your code here
        int[] countingSortArry = new int[101];
        a.forEach(integer -> {countingSortArry[integer] +=1;});
        int max = Integer.MIN_VALUE;
        for(int i=1; i<countingSortArry.length; i++){
            max = Math.max(max,(countingSortArry[i]+countingSortArry[i-1]));
        }
        return max;
    }

}
