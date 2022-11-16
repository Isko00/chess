package backs.pieces;

import backs.Color;
import backs.Piece;
import static java.lang.Math.*;

public class King extends Piece {
	public King(Color color) {
		super(color, "King", "k");
	}

	@Override
	public boolean moveIsObtainable(int[] vector, boolean onTake) {
		return abs(vector[0]) < 2
				&& abs(vector[1]) < 2;
	}
}
