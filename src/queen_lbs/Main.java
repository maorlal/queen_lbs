package queen_lbs;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Game g1 = new Game();
        System.out.println("Enter board size and number of restarts:");
	    g1.play(sc.nextInt() , sc.nextInt());
    }
}
