package hackerrank.math;

public class BulbCount {
    static long lights(int n) {
        long ans=1;
        for(int i=0;i<n;i++){
            ans=ans*2;
            ans=ans%100000;
        }
        return  (ans - 1);
    }

    public static void main(String[] args){
        System.out.println(lights(8291));
    }
}
