package hackerrank.algo;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class StrongPassword {
    // Complete the minimumNumber function below.

    static String numbers = "0123456789";
    static String lower_case = "abcdefghijklmnopqrstuvwxyz";
    static String upper_case = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    static String special_characters = "!@#$%^&*()-+";
    static int[] bitset = new int[]{0,0,0,0};

    static int minimumNumber(int n, String password) {
        int counter=0;
        if(password.length() < 6){
            return (6 - password.length());
        }
        // Return the minimum number of characters to make the password strong
        for(int i=0 ;i < password.length() ;i++){
            if(numbers.indexOf(password.charAt(i)) != -1){
                bitset[0]=1;
            }
            if(lower_case.indexOf(password.charAt(i)) != -1){
                bitset[1]=1;
            }
            if(upper_case.indexOf(password.charAt(i)) != -1){
                bitset[2]=1;
            }if(special_characters.indexOf(password.charAt(i)) != -1){
                bitset[3]=1;
            }

        }

        for(int i=0; i< bitset.length;i++){
            if(bitset[i] != 1) {
                counter++;
            }
        }
        return counter;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("D:\\Backup_C_D\\code\\TestDemo\\src\\hackerrank\\dummy.txt"));

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        String password = scanner.nextLine();

        int answer = minimumNumber(n, password);

        bufferedWriter.write(String.valueOf(answer));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
