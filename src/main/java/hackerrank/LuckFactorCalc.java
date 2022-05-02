package hackerrank;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

class ResultLuckFactor {

    /*
     * Complete the 'luckBalance' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER k
     *  2. 2D_INTEGER_ARRAY contests
     */

    public static int luckBalance(int k, List<List<Integer>> contests) {
        // Write your code here

        contests.sort(Comparator.comparing(integers -> integers.get(0)*-1));

        int maxContextToLose = k;
        int luckFactor = 0;
        for (List<Integer> list : contests) {
            if (list.get(1) == 1) {
                if (maxContextToLose > 0) {
                    maxContextToLose--;
                } else {
                    luckFactor -= list.get(0);
                    continue;
                }
            }
            luckFactor += list.get(0);
        }
        //System.out.println(luckFactor);
        return luckFactor;
    }

}

public class LuckFactorCalc {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("C:\\Users\\conta\\IdeaProjects\\CodingTest\\src\\main\\resources\\test.txt"));

        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int n = Integer.parseInt(firstMultipleInput[0]);

        int k = Integer.parseInt(firstMultipleInput[1]);

        List<List<Integer>> contests = new ArrayList<>();

        IntStream.range(0, n).forEach(i -> {
            try {
                contests.add(
                        Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                                .map(Integer::parseInt)
                                .collect(toList())
                );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        int result = ResultLuckFactor.luckBalance(k, contests);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
