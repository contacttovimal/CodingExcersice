package hackerrank.algo;

import java.util.Scanner;

public class InsertionSort {

    public static void insertionSort(int[] A){
        for(int i = 0; i < A.length; i++){
            int value = A[i];
            int j = i + 1;
            while(j < A.length){
                if(A[j] < value) {
                    A[i] = A[j];
                    A[j] = value;
                    value =  A[i];
                }
                j++;
            }
        }
        printArray(A);
    }


    static void printArray(int[] ar) {
        for(int n: ar){
            System.out.print(n+" ");
        }
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] ar = new int[n];
        for(int i=0;i<n;i++){
            ar[i]=in.nextInt();
        }
        insertionSort(ar);
    }
}
