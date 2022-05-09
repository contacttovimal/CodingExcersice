package hackerrank;

import java.io.*;
import java.util.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

class ResultCandie {

    /*
     * Complete the 'candies' function below.
     *
     * The function is expected to return a LONG_INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER n
     *  2. INTEGER_ARRAY arr
     */

    public static long candies(int n, List<Integer> arr) {
        // Write your code here
        int candiesCounter=0;
        int prevScore=arr.get(0);
        int totalCandies=0;
        for(int i=0; i<arr.size();i++){
            if(prevScore > arr.get(i) ){
                candiesCounter=1;
            }else{
                candiesCounter++;
            }
            System.out.println("score :" + arr.get(i) + " , candiesCounter : " + candiesCounter + ", prevScore :" + prevScore);
            totalCandies += candiesCounter;
            prevScore = arr.get(i);
        }
        return totalCandies;
    }

}

public class CandieCounter {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter( "C:\\Users\\conta\\IdeaProjects\\CodingTest\\src\\main\\resources\\test.txt" ));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> arr = IntStream.range(0, n).mapToObj(i -> {
                    try {
                        return bufferedReader.readLine().replaceAll("\\s+$", "");
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                })
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(toList());

        long result = ResultCandie.candies(n, arr);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}

