package hackerrank.algo;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class ShareLockValidString {
    // Complete the isValid function below.
    static String isValid(String s) {
        Map<Character, Integer> charMap = new TreeMap<>();
        for (int i = 0; i < s.length(); i++) {
            Integer count = count = charMap.getOrDefault(s.charAt(i), 0);
            count++;
            charMap.put(s.charAt(i), count);
        }
        String result = "YES";
        boolean flag = true;
        Integer max = Integer.MIN_VALUE;
        for (Map.Entry<Character, Integer> entry : charMap.entrySet()) {
            if(max < entry.getValue()){
                max = entry.getValue();
            }
        }
        Iterator<Integer> it = charMap.values().iterator(); ;
        Integer prevValue = it.next();
        while(it.hasNext()){
            Integer current = it.next();
            if (Math.abs((prevValue - current)) == 1) {
                if (flag) {
                    flag = false;
                    continue;
                } else {
                    result = "NO";
                    break;
                }
            } else if(Math.abs((prevValue - current)) > 1){
                if(current == 1){
                    if(flag){
                        flag= false;
                        continue;
                    }else{
                        result = "NO";
                        break;
                    }
                }else {
                    result = "NO";
                    break;
                }
            }
            prevValue = current;
        }
        return result;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("D:\\Backup_C_D\\code\\TestDemo\\src\\hackerrank\\dummy.txt"));

        String s = scanner.nextLine();

        String result = isValid(s);

        bufferedWriter.write(result);
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
