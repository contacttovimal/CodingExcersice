package hackerrank;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

class Result {

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
        int board[][] = new int[n][n];

        for(List<Integer> obstacle : obstacles){
            board[obstacle.get(0)-1][obstacle.get(1)-1] = -1;
        }
        r_q = r_q -1;
        c_q = c_q-1;

        moveLeft(board, r_q, c_q-1);
        moveRight(board, r_q, c_q+1);
        moveTop(board, r_q-1, c_q);
        moveBottom(board, r_q+1, c_q);

        moveBottomRight(board, r_q+1, c_q+1);
        moveTopRight(board, r_q-1, c_q+1);
        moveTopLeft(board, r_q-1, c_q-1);
        moveBottomLeft(board, r_q+1, c_q-1);
        int attackCount=0;
        for(int row=0; row<board.length; row++){
            for(int col=0; col<board[0].length; col++){
                if(board[row][col] == 1){
                    attackCount++;
                }
            }
        }
        return attackCount;
    }

    public static void moveLeft(int[][] board,int row, int col){
        if(row < board.length && row >= 0  && col >= 0 && col < board[0].length && board[row][col] == 0){
            board[row][col]=1;
            moveLeft(board,row,col-1);
        }
        return;
    }
    public static void moveRight(int[][] board,int row, int col){
        if(row < board.length && row >= 0  && col >= 0 && col < board[0].length && board[row][col] == 0){
            board[row][col]=1;
            moveRight(board,row,col+1);
        }
        return;
    }
    public static void moveTop(int[][] board,int row, int col){
        if(row < board.length && row >= 0 && col >= 0 && col < board[0].length  &&  board[row][col] == 0){
            board[row][col]=1;
            moveTop(board,row-1,col);
        }
        return;
    }
    public static void moveBottom(int[][] board,int row, int col){
        if(row < board.length && row >= 0 && col >= 0 && col < board[0].length && board[row][col] == 0){
            board[row][col]=1;
            moveBottom(board,row+1,col);
        }
        return;
    }

    public static void moveBottomRight(int[][] board,int row, int col){
        if(row < board.length && row >= 0 && col >= 0 &&col >= 0 && col < board[0].length && board[row][col] == 0){
            board[row][col]=1;
            moveBottomRight(board,row+1,col+1);
        }
        return;
    }
    public static void moveTopRight(int[][] board,int row, int col){
        if(row < board.length && row >= 0 && col >= 0 && col >= 0 && col < board[0].length && board[row][col] == 0){
            board[row][col]=1;
            moveTopRight(board,row-1,col+1);
        }
        return;
    }
    public static void moveTopLeft(int[][] board,int row, int col){
        if(row < board.length && row >= 0 && col >= 0 && col >= 0 && col < board[0].length && board[row][col] == 0){
            board[row][col]=1;
            moveTopLeft(board,row-1,col-1);
        }
        return;
    }
    public static void moveBottomLeft(int[][] board,int row, int col){
        if(row < board.length && row >= 0 && col >= 0 && col >= 0 && col < board[0].length && board[row][col] == 0){
            board[row][col]=1;
            moveBottomLeft(board,row+1,col-1);
        }
        return;
    }
}

public class QueensAttack {
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

        int result = Result.queensAttack(n, k, r_q, c_q, obstacles);
        System.out.println(result);
        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
