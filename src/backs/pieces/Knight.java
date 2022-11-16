package backs.pieces;

import backs.Color;
import backs.Piece;
import static java.lang.Math.*;

public class Knight extends Piece {
	public Knight(Color color) {
		super(color, "Knight", "K");
	}
	
	public Knight() {
		super();
	}
	
	@Override
	public boolean moveIsObtainable(int[] vector, boolean onTake) {
		return (abs(vector[0]) == 2
					&& abs(vector[1]) == 1)
				|| (abs(vector[1]) == 2 
					&& abs(vector[0]) == 1);
	}
	/*
	@Override
	public String toString() {
		return super.toString() + sign;
	}*/
}
