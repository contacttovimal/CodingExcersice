package company.morganstanley;

import java.io.*;
import java.util.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

class Result {

    /*
     * Complete the 'moves' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts INTEGER_ARRAY arr as parameter.
     */

    public static int moves(List<Integer> arr) {
        // Write your code here
        Integer[] array = new Integer[arr.size()];
        arr.toArray(array);
        int swapCounter=0;
        for(int i=0,j=array.length-1; i<j ;){
            if(array[i]%2!=0 && array[j]%2==0 ){
                Integer temp = array[i];
                array[i] = array[j];
                array[j] = temp;
                swapCounter++;
                i++;j--;
            } else if(array[i]%2==0){
                i++;
            } else if(array[j]%2 !=0){
                j--;
            }
        }
        return  swapCounter;
    }

}

public class OddEvenArraySort {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("test.txt"));

        int arrCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> arr = IntStream.range(0, arrCount).mapToObj(i -> {
                    try {
                        return bufferedReader.readLine().replaceAll("\\s+$", "");
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                })
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(toList());

        int result = Result.moves(arr);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }

}
