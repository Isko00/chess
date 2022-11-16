package backs.pieces;

import backs.Color;
import backs.Piece;
import static java.lang.Math.*;

public class Queen extends Piece {
	public Queen(Color color) { super(color, "Queen", "Q"); }

	public Queen() { super(); }
	
	@Override
	public boolean moveIsObtainable(int[] vector, boolean onTake) {
		return (new Bishop(color)).moveIsObtainable(vector, onTake) 
				|| (new Rook(color).moveIsObtainable(vector, onTake));
	}

	@Override
	public int[] getMove(int[] vector) {
		int[] answer = {(int) signum(vector[0]), 
				(int) signum(vector[1])};
		return answer;
	}
}
