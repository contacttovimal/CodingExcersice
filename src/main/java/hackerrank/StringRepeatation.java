package hackerrank;

import java.io.*;
import java.util.stream.IntStream;

class ResultStringRepeatation {

    /*
     * Complete the 'alternatingCharacters' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts STRING s as parameter.
     */

    public static int alternatingCharacters(String s) {
        // Write your code here
        StringBuilder stringBuilder = new StringBuilder(s);
        for(int i=0; i< stringBuilder.length() ; i++){
            while(i < stringBuilder.length()-1 && stringBuilder.charAt(i) == stringBuilder.charAt(i+1)){
                stringBuilder = stringBuilder.deleteCharAt(i+1);
            }
        }
        //System.out.println((s.length() - stringBuilder.length()));
        return s.length() - stringBuilder.length();
    }

}

public class StringRepeatation {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("test.txt"));

        int q = Integer.parseInt(bufferedReader.readLine().trim());

        IntStream.range(0, q).forEach(qItr -> {
            try {
                String s = bufferedReader.readLine();

                int result = ResultStringRepeatation.alternatingCharacters(s);

                bufferedWriter.write(String.valueOf(result));
                bufferedWriter.newLine();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        bufferedReader.close();
        bufferedWriter.close();
    }
}
