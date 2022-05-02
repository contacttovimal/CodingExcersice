package hackerrank;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

class ResultStr {

    /*
     * Complete the 'queensAttack' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER n
     *  2. INTEGER k
     *  3. INTEGER r_q
     *  4. INTEGER c_q
     *  5. 2D_INTEGER_ARRAY obstacles
     */

    public static int queensAttack(int n, int k, int r_q, int c_q, List<List<Integer>> obstacles) {
        // Write your code here
        r_q = r_q -1;
        c_q = c_q-1;

        for(List<Integer> obstacle: obstacles){
            obstacle.set(0, obstacle.get(0)-1);
            obstacle.set(1, obstacle.get(1)-1);
        }

        int counter=0;
        counter += moveLeft(r_q, c_q-1, n,n,r_q,c_q,obstacles);
        counter +=  moveRight( r_q, c_q+1,n,n,r_q,c_q,obstacles);
        counter +=  moveTop(r_q-1, c_q,n,n,r_q,c_q,obstacles);
        counter +=  moveBottom(r_q+1, c_q,n,n,r_q,c_q,obstacles);

        counter +=  moveBottomRight( r_q+1, c_q+1,n,n,r_q,c_q,obstacles);
        counter +=  moveTopRight( r_q-1, c_q+1,n,n,r_q,c_q,obstacles);
        counter +=  moveTopLeft( r_q-1, c_q-1,n,n,r_q,c_q,obstacles);
        counter +=  moveBottomLeft( r_q+1, c_q-1,n,n,r_q,c_q,obstacles);

        return counter;
    }

    public enum DIRECTION {LEFT,RIGHT,TOP,BOTTOM,TOP_LEFT,TOP_RIGHT, BOTTOM_LEFT, BOTTOM_RIGHT}

    public static boolean isValidPoint(int curr_row, int curr_col, List<List<Integer>> obstacles) {
        for (List<Integer> obstacle : obstacles) {
            if((curr_row == obstacle.get(0) && curr_col == obstacle.get(1))){
                return false;
            }
        }
        return true;
    }


    public static int moveLeft(int row, int col, int totalRow, int totalCol, int r_q, int c_q, List<List<Integer>> obstacles){
        int count =0;
        if(row < totalRow && row >= 0  && col >= 0 && col < totalCol){
            if(isValidPoint(row,col,obstacles)) {
                count++;
                count += moveLeft(row, col - 1, totalRow, totalCol,r_q,c_q,obstacles);
            }
        }
        return count;
    }
    public static int moveRight(int row, int col, int totalRow, int totalCol, int r_q, int c_q, List<List<Integer>> obstacles){
        int count =0;
        if(row < totalRow && row >= 0  && col >= 0 && col < totalCol){
            if(isValidPoint(row,col,obstacles)) {
                count++;
                count += moveRight(row, col+1, totalRow, totalCol, r_q, c_q, obstacles);
            }
        }
        return count;
    }
    public static int moveTop(int row, int col, int totalRow, int totalCol, int r_q, int c_q, List<List<Integer>> obstacles){
        int count =0;
        if(row < totalRow && row >= 0 && col >= 0 && col < totalCol  ){
            if(isValidPoint(row,col,obstacles)) {
                count++;
                count += moveTop(row - 1, col, totalRow, totalCol, r_q, c_q, obstacles);
            }
        }
        return count;
    }
    public static int moveBottom(int row, int col, int totalRow, int totalCol, int r_q, int c_q, List<List<Integer>> obstacles){
        int count =0;
        if(row < totalRow && row >= 0 && col >= 0 && col < totalCol){
            if(row < totalRow && row >= 0 && col >= 0 && col < totalCol  ) {
                if(isValidPoint(row,col,obstacles)) {
                    count++;
                    count += moveBottom(row + 1, col, totalRow, totalCol, r_q, c_q, obstacles);
                }
            }
        }
        return count;
    }

    public static int moveBottomRight(int row, int col, int totalRow, int totalCol, int r_q, int c_q, List<List<Integer>> obstacles){
        int count =0;
        if(row < totalRow && row >= 0 && col >= 0 &&col >= 0 && col < totalCol){
            if(isValidPoint(row,col,obstacles)) {
                count++;
                count += moveBottomRight(row + 1, col + 1, totalRow, totalCol, r_q, c_q, obstacles);
            }
        }
        return count;
    }
    public static int moveTopRight(int row, int col, int totalRow, int totalCol, int r_q, int c_q, List<List<Integer>> obstacles){
        int count =0 ;
        if(row < totalRow && row >= 0 && col >= 0 && col >= 0 && col < totalCol){
            if(isValidPoint(row,col,obstacles)) {
                count++;
                count += moveTopRight( row - 1, col + 1, totalRow, totalCol, r_q, c_q, obstacles);
            }
        }
        return count;
    }
    public static int moveTopLeft(int row, int col, int totalRow, int totalCol, int r_q, int c_q, List<List<Integer>> obstacles){
        int count=0;
        if(row < totalRow && row >= 0 && col >= 0 && col >= 0 && col < totalCol){
            if(isValidPoint(row,col,obstacles)) {
                count++;
                count += moveTopLeft(row - 1, col - 1, totalRow, totalCol, r_q, c_q, obstacles);
            }
        }
        return count;
    }
    public static int moveBottomLeft(int row, int col, int totalRow, int totalCol, int r_q, int c_q, List<List<Integer>> obstacles){
        int count=0;
        if(row < totalRow && row >= 0 && col >= 0 && col >= 0 && col < totalCol){
            if(isValidPoint(row,col,obstacles)) {
                count++;
                count +=moveBottomLeft(row + 1, col - 1, totalRow, totalCol, r_q, c_q, obstacles);
            }
        }
        return count;
    }
}

public class QueensAttackWithOutArry {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("C:\\Users\\conta\\IdeaProjects\\CodingTest\\src\\main\\resources\\test.txt"));

        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int n = Integer.parseInt(firstMultipleInput[0]);

        int k = Integer.parseInt(firstMultipleInput[1]);

        String[] secondMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int r_q = Integer.parseInt(secondMultipleInput[0]);

        int c_q = Integer.parseInt(secondMultipleInput[1]);

        List<List<Integer>> obstacles = new ArrayList<>();

        IntStream.range(0, k).forEach(i -> {
            try {
                obstacles.add(
                        Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                                .map(Integer::parseInt)
                                .collect(toList())
                );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        int result = ResultStr.queensAttack(n, k, r_q, c_q, obstacles);

        System.out.println(result);
        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
