package backs;

public abstract class Piece {
    protected Color color;
    protected String name, sign;
    // it has meaning for pawn and castling
    protected boolean firstMove = true;

    // Constructor, sets all values to variables
    public Piece(Color color, String name, String sign) {
        this.color = color;
		this.name = name;
		this.sign = sign;
    }

    public Piece() {
    	this(Color.WHITE, " ", " ");
    }
    
    public boolean getFirstMove() {return firstMove;}

    // Makes vector from two points
    public int[] vectorOf(int[][] move) {
        int[] from = move[0], to = move[1];
        int[] answer = {to[0] - from[0], to[1] - from[1]};
        return answer;
    }

    public int[] vectorOf(Pair from, Pair to) {
        int[] intFrom = {from.i(), from.j()},
                intTo = {to.i(), to.j()};
        int[] answer = {intTo[0] - intFrom[0], intTo[1] - intFrom[1]};
        
        return answer;
    }

    // Returns true if move is obtainable for this figure
    public abstract boolean moveIsObtainable(int[] vector, boolean onTake);

    // Returns vector of unit move
	public int[] getMove(int[] vector) {
		return vector;
	}

    public Color getOpponentsColor() {
        if (this.color == Color.WHITE) {
            return Color.BLACK;
        } else {
            return Color.WHITE;
        }
    }

    // returns color
    public Color getColor() { return color; }

    /* After first move you block
    *  opportunity of "first" move types
    */
    public void firstMoveDone() { firstMove = false; }

    public void firstMoveUndone() { firstMove = true; }

    // Returns name of piece
    public String getName() { return sign; }

    @Override
    public String toString() {
    	return color.toString() + sign;
    }
}
