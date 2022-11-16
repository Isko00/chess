package backs.pieces;

import backs.Color;
import backs.Piece;

public class EmptyPiece extends Piece {
	public EmptyPiece() {
		super(Color.EMPTY, " ", " ");
	}

	@Override
	public boolean moveIsObtainable(int[] vector, boolean onTake) {
		return false;
	}

}
