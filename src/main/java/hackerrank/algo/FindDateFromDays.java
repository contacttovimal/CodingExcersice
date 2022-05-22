package hackerrank.algo;

import java.io.*;

public class FindDateFromDays {
    static int[] leapYearDays=new int[]{31,29,31,30,31,30,31,31,30,31,30,31};
    static int[] noLeapYearDays=new int[]{31,28,31,30,31,30,31,31,30,31,30,31};
    static String[] months = new String[]{"01","02","03","04","05","06","07","08","09","10","11","12"};

    static String dayOfProgrammer(int year) {
        String ddMMyyy="";
        int tmpYear=256;
        int month=0;
        if (year == 1918)
            return "26.09.1918";
        else if((year < 1917 && year %4 ==0) || ((year > 1918) && (year % 400 ==0 ||  (year%4==0 && year %100 !=0)))){
            while(tmpYear > leapYearDays[month]){
                tmpYear -= leapYearDays[month++];
            }
         }else {
            while(tmpYear > noLeapYearDays[month]){
                tmpYear -= noLeapYearDays[month++];
            }
        }
        ddMMyyy = tmpYear + "."+  months[month] + "." + year;
        return ddMMyyy;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("D:\\Backup_C_D\\code\\TestDemo\\src\\hackerrank\\dummy.txt"));

        int year = Integer.parseInt(bufferedReader.readLine().trim());

        String result = dayOfProgrammer(year);

        System.out.println(result);

        bufferedWriter.write(result);
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
