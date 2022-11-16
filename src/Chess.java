import backs.*;
import front.*;

public class Chess {
	public static void main(String[] args) {
        Board board = new Board();
        GameProcess game = new GameProcess(board);
        ConsoleOutput out = new ConsoleOutput(board);
        Reader in = new Reader();
        out.print();

        while (game.notEnd()) {
        	Pair[] move = in.formattedInput();
            try {
                game.move(move[0], move[1]);
                out.print();
            } catch(WrongMoveExeption ex) {
                System.out.println(ex);
            }
        }

        System.out.println(game.reason());
	}
}