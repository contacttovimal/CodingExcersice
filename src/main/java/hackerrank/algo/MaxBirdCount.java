package hackerrank.algo;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MaxBirdCount {

    // Complete the migratoryBirds function below.
    static int migratoryBirds(List<Integer> arr) {
        TreeMap<Integer,Integer> treemap = new TreeMap<>();

        for(Integer i:arr){
            Integer counter=treemap.getOrDefault(i,0);
            treemap.put(i,++counter);
        }
        int max = Integer.MIN_VALUE;
        int maxKey = -1;
        Iterator<Map.Entry<Integer,Integer>> treeIT = treemap.entrySet().iterator();
        while(treeIT.hasNext()){
            Map.Entry<Integer,Integer> entry = treeIT.next();
            Integer highestCount = entry.getValue();
            Integer highestKey = entry.getKey();
            if(max < highestCount){
                max = highestCount;
                maxKey = highestKey;
            }
            if(max == highestCount && highestKey < maxKey){
                max = highestCount;
                maxKey = highestKey;
            }
        }
        return  maxKey;
    }

    class TreeKey implements Comparator<Integer>{
        private Integer integer = -1;
        public TreeKey(Integer integer){
            this.integer = integer;
        }

        @Override
        public int compare(Integer o1, Integer o2) {
            return o1 - o2;
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int arrCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> arr = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        int result = migratoryBirds(arr);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
