package hackerrank.algo;

import jdk.nashorn.internal.objects.NativeNumber;

import java.util.ArrayList;
import java.util.List;

public class ArraySplit {

    public static void splitArray(List<Integer> numbers) {
        for(int i=0; i<=numbers.size();i++){
            for(int j=i; j<=numbers.size();j++){
                for(int k=i; k<j;k++) {
                    System.out.print(numbers.get(k)+",");
                }
                System.out.println("");
            }
        }
    }

    public static void printArray(List<Integer> numbers, int start, int end){
        for(int k=0; k<(end) && k < numbers.size();k++){
            System.out.print(numbers.get(k) + ",");
        }
    }

    public static void main(String[] args) {
        List<Integer> numList = new ArrayList<>();
        numList.add(1);numList.add(2);numList.add(3);numList.add(4);
        splitArray(numList);
    }
}
