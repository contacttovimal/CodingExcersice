package hackerrank;

import java.io.*;
import java.util.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;



class ResultArryDiff {

    /*
     * Complete the 'minimumAbsoluteDifference' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts INTEGER_ARRAY arr as parameter.
     */

    public static int minimumAbsoluteDifference(List<Integer> arr) {
        // Write your code here
        int min1 = Integer.MAX_VALUE;
        Collections.sort(arr);

        for(int i=0; i<arr.size()-1;i++){
            if (min1 > (Math.abs(arr.get(i) - arr.get(i+1)))) {
                min1 = (Math.abs(arr.get(i) - arr.get(i+1)));
            }
        }
       /* for(int i=0;i<arr.size();i++){
            for(int j=i+1;j<arr.size();j++){
                if(min1 > (Math.abs(arr.get(i) - arr.get(j)))){
                    min1 = (Math.abs(arr.get(i) - arr.get(j)));
                }
            }
        }*/
        //System.out.println(min1);
        return min1;
    }

}

public class MinDiffArry {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(("C:\\Users\\conta\\IdeaProjects\\CodingTest\\src\\main\\resources\\test.txt")));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> arr = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                .map(Integer::parseInt)
                .collect(toList());

        int result = ResultArryDiff.minimumAbsoluteDifference(arr);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
