package hackerrank.algo;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

public class UniformStringWithWeight {

    // Complete the weightedUniformStrings function below.
    static String[] weightedUniformStrings(String s, int[] queries) {
        HashSet<Integer> weightSet = new HashSet<>();
        char prev='^';
        int uniformWeight=0;
        StringBuilder uniform= new StringBuilder("");
        for(int i=0; i< s.length(); i++){
            if(prev == s.charAt(i)){
                uniform.append(s.charAt(i));
                uniformWeight +=  (s.charAt(i)-96);
                weightSet.add(uniformWeight);
            }else{
                uniformWeight = (s.charAt(i)-96);
                weightSet.add(uniformWeight);
                uniform.delete(0, uniform.length());
                uniform.append(s.charAt(i));
            }
            prev = s.charAt(i);

        }
        List<String> result = new ArrayList<>();
        for(int i=0; i<queries.length; i++){
            if(weightSet.contains(queries[i])){
                result.add("Yes");
            }else{
                result.add("No");
            }
        }
        return result.toArray(new String[result.size()]);
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("D:\\Backup_C_D\\code\\TestDemo\\src\\hackerrank\\dummy.txt"));

        String s = scanner.nextLine();

        int queriesCount = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] queries = new int[queriesCount];

        for (int i = 0; i < queriesCount; i++) {
            int queriesItem = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
            queries[i] = queriesItem;
        }

        String[] result = weightedUniformStrings(s, queries);


        for (int i = 0; i < result.length; i++) {
            bufferedWriter.write(result[i]);
            System.out.println(result[i]);
            if (i != result.length - 1) {
                bufferedWriter.write("\n");
            }
        }

        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
