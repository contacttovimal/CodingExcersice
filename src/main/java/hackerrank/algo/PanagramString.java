package hackerrank.algo;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;

public class PanagramString {

    static String pangrams(String s) {
        HashSet<Character> dataSet =new HashSet<>();
        s = s.toUpperCase();
        for(int i=0; i<s.length(); i++){
            if(s.charAt(i) >= 65 && s.charAt(i) <= 90) {
                dataSet.add(s.charAt(i));
            }
        }
        if(dataSet.size()==26){
            return "pangram";
        }else{
            return  "not pangram";
        }
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("D:\\Backup_C_D\\code\\TestDemo\\src\\hackerrank\\dummy.txt"));

        String s = scanner.nextLine();

        String result = pangrams(s);

        bufferedWriter.write(result);
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
