package hackerrank.algo;

import java.io.*;

public class CountVally {

    /*public static int countingValleys(int steps, String path) {
        // Write your code here
        int valleyCounter=0;
        int index=0;
        while(path.indexOf("DDU",index)!=-1){
            valleyCounter++;
            index = path.indexOf("DDU",index)+2;
        }
        return valleyCounter;
    }*/

    public static int countingValleys(int steps, String path) {
        int v = 0;     // # of valleys
        int lvl = 0;   // current level
        for(char c : path.toCharArray()){
            if(c == 'U') ++lvl;
            if(c == 'D') --lvl;

            // if we just came UP to sea level
            if(lvl == 0 && c == 'U')
                ++v;
        }
        return v;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("D:\\Backup_C_D\\code\\TestDemo\\src\\hackerrank\\dummy.txt"));

        int steps = Integer.parseInt(bufferedReader.readLine().trim());

        String path = bufferedReader.readLine();

        int result = CountVally.countingValleys(steps, path);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
