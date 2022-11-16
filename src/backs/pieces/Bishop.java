package backs.pieces;

import backs.Color;
import backs.Piece;
import static java.lang.Math.*;

public class Bishop extends Piece {
	public Bishop(Color color) {
		super(color, "Bishop", "B");
	}

	@Override
	public boolean moveIsObtainable(int[] vector, boolean onTake) {
		return abs(vector[1]) == abs(vector[0]);
	}

	@Override
	public int[] getMove(int[] vector) {
		int[] answer = {(int) signum(vector[0]), 
				(int) signum(vector[1])};
		return answer;
	}
}