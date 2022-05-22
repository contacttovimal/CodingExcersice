package hackerrank.algo;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.Scanner;

public class HighestValuePallendrome {

    // Complete the highestValuePalindrome function below.
    static String highestValuePalindrome(String s, int n, int k) {
        int counter=k;
        char[] charArry = s.toCharArray();
        for(int i=0,j=charArry.length-1;i<j && counter>0; i++,j--){
            if(charArry[i] != charArry[j]){
                if(charArry[i]!='9'){
                    charArry[i]='9';
                    counter--;
                }
                if(charArry[j]!='9'){
                    charArry[j]='9';
                    counter--;
                }
            }
        }
        if(charArry.length%2!=0){
            if(charArry[(charArry.length/2)] != '9' && counter > 0){
                charArry[(charArry.length/2)]='9';
                counter--;
            }
        }
        if(counter > 0){
            for(int i=0,j=charArry.length-1;i<j && counter>0; i++,j--){
                    if(charArry[i] !='9' && charArry[j] !='9') {
                        charArry[i] = '9';
                        counter--;
                        charArry[j] = '9';
                        counter--;
                    }
            }
        }
        for(int i=0,j=charArry.length-1;i<j; i++,j--){
            if(charArry[i] != charArry[j]){
                return "-1";
            }
        }
        return String.valueOf(charArry);
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        System.out.println(new Date());
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("D:\\Backup_C_D\\code\\TestDemo\\src\\hackerrank\\dummy.txt"));

        String[] nk = scanner.nextLine().split(" ");

        int n = Integer.parseInt(nk[0]);

        int k = Integer.parseInt(nk[1]);

        String s = scanner.nextLine();

        String result = highestValuePalindrome(s, n, k);

        System.out.println(result);

        bufferedWriter.write(result);
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
