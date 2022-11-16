package backs;

import backs.pieces.*;

public class Board {
    private Tile[][] board = new Tile[8][8];
    private TileSet whites = new TileSet();
    private TileSet blacks = new TileSet();

    // Fills board array and color arrays with tiles
    {
        Color pieceColor, tileColor;

        for (int i = 0; i < 8; i++) {
            /* At beginning of game white pieces are on
            *  first two rows and black pieces on last two rows
            */
            if (i <= 3) { pieceColor = Color.WHITE; }
            else { pieceColor = Color.BLACK; }

            for (int j = 0; j < 8; j++) {

                // Every second Tile is black, starting with [0, 0] 
                if ((i + j) % 2 == 0) { tileColor = Color.BLACK; }
                else { tileColor = Color.WHITE; }

                // New pair of coordinates for figures
                Pair pair = new Pair(i, j);
                Piece piece = new EmptyPiece();

                // All first and sixth rows are filled with pawns
                if (i == 1 || i == 6) {
                    piece = new Pawn(pieceColor);
                    //board[i][j] = new Tile(null, tileColor, pair);
                // Other figures are on zero and last row
                } else if (i == 0 || i == 7) {
                    if (j == 0 || j == 7) {
                    	piece = new Rook(pieceColor);
                    } else if (j == 1 || j == 6) {
                        piece = new Knight(pieceColor);
                    } else if (j == 2 || j == 5) {
                        piece = new Bishop(pieceColor);
                    } else if (j == 3) {
                        piece = new Queen(pieceColor);
                    } else if (j == 4) {
                        piece = new King(pieceColor);
                    }
                }
                // Other tiles have empty Piece attribute

                board[i][j] = new Tile(piece, tileColor, pair);

                sortByColor(board[i][j]);
            }
        }
    }

    public Board() {}

    public Tile[][] getBoard() {return board;}

    // Fills TileSets with pieces
    public void sortByColor(Tile tile) {
        Piece piece = tile.getPiece();
        
        if (piece != null) {
            if (piece.getColor() == Color.WHITE) {
                whites.add(tile);
            } else {
                blacks.add(tile);
            }
        }
    }

    public Tile getKingsTile(Color color) {
        Tile answer = null;
        TileSet arr = getTileSet(color);

        for (int i = 0; i < arr.size(); i++) {
            answer = arr.get(i);
            if (answer.getPiece() instanceof King) {
            	return answer;
            }
        }
        String colorName = ( (color == Color.WHITE) ? "WHITE" : "BLACK");
        throw new NullPointerException("NO " + colorName + " KING FOUND!");
    }

    // Return tile of exact indices in "board" array
    public Tile getTileByPair(Pair pair) {
        Tile answer = board[pair.i()][pair.j()];
        return answer;
    }

    // Return figure of exact indices in "board" array
    public Piece getPieceByPair(Pair pair) {
        Piece answer = getTileByPair(pair).getPiece();
        return answer;
    }

    // Moves piece from one Tile to another
    public void transfer(Tile from, Tile to) {
        Piece fromPiece = from.getPiece();
        Piece toPiece = to.getPiece();
        fromPiece.firstMoveDone();

        if (toPiece != null) {
            TileSet toArr = getTileSet(toPiece.getColor());
            int toIndex = toArr.indexOf(to);

            if (toIndex != -1) {toArr.erase(toIndex);}
        }

        TileSet fromArr = getTileSet(fromPiece.getColor());
        int fromIndex = fromArr.indexOf(from);
        to.setPiece(fromPiece);
        fromArr.set(fromIndex, to);
        from.clear();
    }

    public void transfer(Pair from, Pair to) {
        Tile fromTile = getTileByPair(from);
        Tile toTile = getTileByPair(to);
        transfer(fromTile, toTile);
    }

    public void antiTransfer(Tile from, Tile to, 
            Piece toPiece, int toIndex) {
        Piece fromPiece = to.getPiece();
        fromPiece.firstMoveUndone();

        if (toPiece != null) {
            TileSet toArr = getTileSet(toPiece.getColor());
            toArr.add(toIndex, to);
        }

        TileSet fromArr = getTileSet(fromPiece.getColor());
        int fromIndex = fromArr.indexOf(to);
        to.setPiece(toPiece);
        fromArr.set(fromIndex, from);
        from.setPiece(fromPiece);
    }

    public void antiTransfer(Pair from, Pair to, 
            Piece toPiece, int toIndex) {
        Tile fromTile = getTileByPair(from);
        Tile toTile = getTileByPair(to);
        antiTransfer(fromTile, toTile, toPiece, toIndex);
    }

    public boolean roadClear(Pair from, Pair to) {
        boolean answer = true;
        Piece fromPiece = getPieceByPair(from);
        int[] vector = fromPiece.vectorOf(from, to);
        int[] unitMove = fromPiece.getMove(vector);
        Pair unitMovePair = new Pair(unitMove);
        Pair step = from;

        while (!step.equals(to)) {
            step = step.add(unitMovePair);
            if (!(getPieceByPair(step) instanceof EmptyPiece) 
                    && !step.equals(to)) {
                answer = false;
                break;
            }
        }

        return answer;
    }

    // Getter for TileSet attributes
    public TileSet getTileSet(Color color) {
        TileSet answer;

        if (color == Color.WHITE) {
            answer = whites;
        } else {
            answer = blacks;
        }

        return answer;
    }
}
