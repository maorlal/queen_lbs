package queen_lbs;
import java.util.Random;


public class Game {
    char [][] board;
    int score;
    int restart_count = 0;


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

    public void play(int _restart_count){
        for (int i=0; i==_restart_count ; i++) {
            System.out.println("Restart "+restart_count+" Step "+i);

        }
        System.out.println("FAIL after "+restart_count+" restarts");
    }

    public void printBoard(){
        for (int i=0 ; i<board.length ; i++) {
            for (int j = 0; j < board.length; j++) {
                System.out.print(board[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println(assessBoard(board));

    }

    public int assessBoard(char[][] _board){
        int  rowS=0 , leftDiagS=0, rightDiagS=0 , qS=0 , ret=0;

        for (int i=0 ; i<board.length ; i++) {
            for (int j = 0; j < board.length; j++) {
                if(_board[i][j]=='Q') rowS++;
            }
            if (rowS>1) ret+=rowS-1;
            if(_board[i][i]=='Q') leftDiagS++;
            if(_board[board.length-i-1][board.length-i-1]=='Q') rightDiagS++;
            rowS=0;
        }

        return ret;
    }

//public boolean checkCol(){
//        for (int i=0 ; i<this.board.length ; i++){
//
//        }
//}
//
}
