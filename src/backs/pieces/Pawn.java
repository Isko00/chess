package backs.pieces;

import static java.lang.Math.signum;

import backs.Color;
import backs.Piece;

public class Pawn extends Piece {
	public Pawn(Color color) { super(color, "Pawn", "P"); }

	public Pawn() { super(); }

	@Override
	public boolean moveIsObtainable(int[] vector, boolean onTake) {
		boolean answer = false;

		/* Pawn can only move in one direction, white and black 
		*  pawns have same move rules, with only direction dependence,
		*  which is dependent to it's color,
		*  so direction of black pawn had been
		*  converted to white one's.
		*/
		if (color == Color.BLACK) {vector[0] *= -1;}

		// Only pawn eats and moves in different ways
		if (!onTake) {
			if (super.firstMove 
					&& (vector[0] == 1 || vector[0] == 2)
					&& vector[1] == 0) {
				answer = true;
			} else if (!super.firstMove
					&& vector[0] == 1
					&& vector[1] == 0) {
				answer = true;
			}
		} else {
			if ((vector[1] == 1 || vector[1] == -1)
					&& vector[0] == 1) {
				answer = true;
			}
		}

		return answer;
	}

	@Override
	public int[] getMove(int[] vector) {
		int[] answer = {(int) signum(vector[0]), 
				(int) signum(vector[1])};
		return answer;
	}
}