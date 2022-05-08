package hackerrank;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class FindQueryCount {

    // Complete the freqQuery fu nction below.
    static List<Integer> freqQuery(List<List<Integer>> queries) {
        Map<Integer,Integer> dataMap = new HashMap<>();
        List<Integer> counterList = new ArrayList<>();
        int totalCounter=0;
        for(List<Integer> query : queries){
            if(query.get(0) == 1){
                int counter= dataMap.getOrDefault(query.get(1), 0) +1;
                dataMap.put(query.get(1), counter);
            }else if(query.get(0) == 2){
                int counter= dataMap.getOrDefault(query.get(1), 0);
                if(counter > 0 ){
                    dataMap.put(query.get(1), counter-1);
                }
            }else if(query.get(0) == 3){
                if(query.get(1) < totalCounter && dataMap.containsValue(query.get(1))
                        && dataMap.values().stream().filter(value->query.get(1)==value).findFirst().isPresent()) {
                    counterList.add(1);
                }else{
                    counterList.add(0);
                }
            }
        };
        return counterList;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("C:\\Users\\conta\\IdeaProjects\\CodingTest\\src\\main\\resources\\test"));

        int q = Integer.parseInt(bufferedReader.readLine().trim());

        List<List<Integer>> queries = new ArrayList<>();

        IntStream.range(0, q).forEach(i -> {
            try {
                queries.add(
                        Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                                .map(Integer::parseInt)
                                .collect(toList())
                );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        List<Integer> ans = freqQuery(queries);

        bufferedWriter.write(
                ans.stream()
                        .map(Object::toString)
                        .collect(joining("\n"))
                        + "\n"
        );

        bufferedReader.close();
        bufferedWriter.close();
    }
}
