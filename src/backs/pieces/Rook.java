package backs.pieces;

import backs.Color;
import backs.Piece;
import static java.lang.Math.*;

public class Rook extends Piece {
	public Rook(Color color) { super(color, "Rook", "R"); }

	public Rook() { super(); }
	
	@Override
	public boolean moveIsObtainable(int[] vector, boolean onTake) {
		return vector[0] == 0
				|| vector[1] == 0;
	}

	@Override
	public int[] getMove(int[] vector) {
		int[] answer = {(int) signum(vector[0]), 
				(int) signum(vector[1])};
		return answer;
	}

}