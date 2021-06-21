package queen_lbs;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Random;


public class Game {
    char [][] board;

    public Game() {
        this.board = new char[0][0];
    }
    public Game( int board_size) {
        this.board = new char[board_size][board_size];
        Random rand = new Random();
        for (int i=0 ; i<board_size ; i++){
            int rand_int = rand.nextInt(board_size-1);
            for (int j=0 ; j<board_size ; j++) {
                if (j==rand_int)
                    board[j][i] = 'Q';
                else
                    board[j][i] = 'X';
            }
        }
    }

    public void play(int _restart_count , int _board_size){
        int n = board.length , i , score=0 , next=0 ,restarts=0;
        boolean exit;
        while (restarts<_restart_count) {
            this.randomBoard(_board_size);
            i=0;
            exit=false;
            while (!exit){
                System.out.println("Restart "+restarts+" Step "+i+"\n");
                score = this.printBoard();
                if (score==0){
                    System.out.println("SOLUTION "+i+"\n");
                    return;
                }
                next = assessBoard(this.board);
                if (next>=score) exit=true;
                i++;
            }
            restarts++;
        }
        System.out.println("FAIL after "+restarts+" restarts\n");
    }
    public  int assessBoard(char[][] _board){
        int min = assessScore(_board);
        int [] nextQcell = new int[2];
        int[][] calc = new int[_board.length][_board.length];
        for (int i=0 ; i< calc.length ; i++){
            for (int j=0 ; j< calc.length ; j++){
                char [][] temp = copyBoard(_board);
                makeQ(temp,i,j);
                calc[i][j] = assessScore(temp);
            }
        }
        for (int i=0 ; i< calc.length ; i++) {
            for (int j = 0; j < calc.length; j++) {
                if (calc[i][j]<=min){
                    min=calc[i][j];
                    nextQcell[0]=i;
                    nextQcell[1]=j;
                }
            }
        }
        makeQ(_board,nextQcell[0],nextQcell[1]);
        return min;
    }
    public int printBoard(){
        int score = assessScore(board);
        for (int i=0 ; i<board.length ; i++) {
            for (int j = 0; j < board.length; j++) {
                System.out.print(board[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println("\nScore = "+score+"\n-----------\n");
        return score;
    }
    public int printBoard(char[][]_board){
        for (int i=0 ; i<_board.length ; i++) {
            for (int j = 0; j < _board.length; j++) {
                System.out.print(_board[i][j]+" ");
            }
            System.out.println();
        }
        return assessScore(board);
    }
    public int assessScore(char[][] _board){
        int  rowS=0 , trDiagS=0, tlDiagS=0 , brDiagS=0 ,blDiagS=0 ,  ret=0 ,n=board.length;


        for (int i=0 ; i<n ; i++) {
            for (int j = 0; j < n; j++) {
                if(_board[i][j]=='Q') rowS++;
            }
            if (rowS>1) ret+=rowS-1;
            rowS=0;
        }

        for (int i=0 ; i<n ; i++) {
            for (int j = i; j >= 0; j--) {
                if(_board[j][i-j]=='Q') tlDiagS++;
                if(_board[i-j][n-1-j]=='Q') trDiagS++;
                if (i<n-1){
                    if(_board[n-1-i+j][n-1-j]=='Q') brDiagS++;
                    if(_board[n-1-j][i-j]=='Q') blDiagS++;
                }
            }
            if (tlDiagS>1) ret+=tlDiagS-1;
            if (trDiagS>1) ret+=trDiagS-1;
            if (brDiagS>1) ret+=brDiagS-1;
            if (blDiagS>1) ret+=blDiagS-1;
            tlDiagS=0;
            trDiagS=0;
            brDiagS=0;
            blDiagS=0;
        }
        //tests
        //System.out.println("\nrightDiagS: "+rightDiagS+"\nleftDiagS: "+leftDiagS);
        //tests
        return ret;
    }
    public char[][] copyBoard(char[][] _board){
        char [][] temp = new char[_board.length][_board.length];
        for (int i=0 ; i< _board.length ; i++){
            for (int j=0 ; j< _board.length ; j++){
                temp[i][j]=_board[i][j];
            }
        }
        return temp;
    }

    private void makeQ(char[][] _board, int row, int col) {
        for (int i=0 ; i< _board.length ; i++){
            if (i==row) _board[i][col] = 'Q';
            else _board[i][col] = 'X';
        }
    }
    private void randomBoard(int board_size){
        this.board = new char[board_size][board_size];
        Random rand = new Random();
        for (int i=0 ; i<board_size ; i++){
            int rand_int = rand.nextInt(board_size-1);
            for (int j=0 ; j<board_size ; j++) {
                if (j==rand_int)
                    board[j][i] = 'Q';
                else
                    board[j][i] = 'X';
            }
        }
    }


}
