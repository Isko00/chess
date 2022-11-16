package backs;

public class Tile {
    private Piece piece;
    private Color color;
    // Stores combination of indexes of this tile in "board" array
    private final Pair pair;
    private Pair enPassantTarget = null;

    // Constructor setting values of all attributes
    public Tile(Piece piece, Color color, Pair pair) {
        this.piece = piece;
        this.color = color;
        this.pair = pair;
    }

    // Setter for Piece attribute
    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    // Getter for Piece attribute
    public Piece getPiece() { return piece; }

    // Getter for Color attribute
    public Color getColor() { return color; }

    // Getter for Pair attribute
    public Pair getPair() { return pair; }
    
    // Returns name of Piece stored
    @Override
    public String toString() {
        String pieceName;

        if (piece != null) {
            pieceName = piece.toString();
        } else {
            // Name of empty piece is two spaces
        	pieceName = "  ";
        }

        if (color == Color.WHITE) {
        	return "|*" + pieceName + "*";
        } else {
        	return "| " + pieceName + " ";
        }
    }

    // Compares pieces by colors
    public boolean pieceColorEquals(Tile tile) {
        boolean answer;

        if (tile.getPiece() == null || piece == null) {
            answer = false;
        } else if (tile.getPiece().getColor() != piece.getColor()){
            answer = false;
        } else {
            answer = true;
        }

        return answer;
    }


    // Compares by values stored in Pair attribute
    public boolean equals(Tile tile) {
        return pair.equals(tile.getPair());
    }

    // Exempts Piece attribute
    public void clear() {this.piece = null;}

    public void setEnPassantTarget(Pair pair) {
        this.enPassantTarget = pair;
    }

    public Pair getEnPassantTarget() {
        return this.enPassantTarget;
    }

    public boolean isEnPassant() {
        return (this.enPassantTarget != null);
    }
}
