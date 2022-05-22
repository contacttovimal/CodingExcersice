package careercup;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class JPTest {
    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in, StandardCharsets.UTF_8);
        BufferedReader in = new BufferedReader(reader);
        String line;
        while ((line = in.readLine()) != null) {
            long inputNum = Long.parseLong(line.trim());
            System.out.println(splitAndCount(inputNum,0));
        }

    }

    public static long splitAndCount(long n, long total){
        if(n == 1){
            return total;
        }
        long a = n/2;
        long b = n -a;
        total += (a * b) ;
        total = splitAndCount(a, total);
        total = splitAndCount(b, total);
        return total;
    }
}
