import java.io.*;
import java.util.*;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class CountTriplet {

    // Complete the countTriplets function below.
    static long countTriplets(List<Long> arr, long r) {
        int result =0;
        Map<Long,Long> leftMap = new HashMap<>();
        Map<Long,Long> rightMap = new HashMap<>();

        for(long item:arr){
            rightMap.put(item, rightMap.getOrDefault(item,0L)+1);
        }

        for(int i=0; i<arr.size(); i++){
            long midItem = arr.get(i);
            long leftElement = 0 , rightElement=0;
            rightMap.put(midItem,rightMap.getOrDefault(midItem,0L)-1);
            if(leftMap.containsKey(midItem/r) && (midItem%r==0)){
                leftElement = leftMap.get(midItem/r);
            }
            if(rightMap.containsKey(midItem*r)){
                rightElement = rightMap.get(midItem * r);
            }
            result += (leftElement * rightElement);
            leftMap.put(midItem, leftMap.getOrDefault(midItem,0L) + 1);
        }
        return result;

    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("test.txt"));

        String[] nr = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int n = Integer.parseInt(nr[0]);

        long r = Long.parseLong(nr[1]);

        List<Long> arr = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                .map(Long::parseLong)
                .collect(toList());

        long ans = countTriplets(arr, r);

        bufferedWriter.write(String.valueOf(ans));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
