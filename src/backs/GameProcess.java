package backs;

import backs.pieces.*;

public class GameProcess {
    private Board board;
    public boolean gameOver = false;
    private Color moveColor = Color.WHITE;
    public boolean isCheck = false;

    public GameProcess(Board board) {this.board = board;}

    public void move(Pair from, Pair to) throws WrongMoveExeption {
        Tile fromTile = board.getTileByPair(from);
        Tile toTile = board.getTileByPair(to);
        
        if (fromTile.getPiece() == null) {
            throw new WrongMoveExeption("Empty field selected!");
        } else if (fromTile.getPiece().getColor() != moveColor) {
            throw new WrongMoveExeption("That is not your figure!");
        } else if (fromTile.pieceColorEquals(toTile)) {
            throw new WrongMoveExeption("Can't eat same colored figure!");
        } else if (moveOpensKingForCheck(from, to)) {
            throw new WrongMoveExeption("It causes check for your king!");
        } else if (isCastling(from, to)) {
            castling(from, to);
/*        } else if (isEnPassant(from, to)) {
            enPassant(from, to);*/
        } else {
            Piece fromPiece = fromTile.getPiece();
            int[] vector = fromPiece.vectorOf(from, to);
            boolean onTake = !(toTile.getPiece() instanceof EmptyPiece);
            if (!fromPiece.moveIsObtainable(vector, onTake)) {
                throw new WrongMoveExeption("That piece moves differently!");
            } else if (!board.roadClear(from, to)) {
                throw new WrongMoveExeption("Obstacle on road!");
            } else {
                board.transfer(fromTile, toTile);
                changeSide();
                lookForCheck();
                checkAvailableMoves();
            }
        }
    }

    public boolean goodMove(Pair from, Pair to) {
        boolean answer = true;
        Tile fromTile = board.getTileByPair(from);
        Tile toTile = board.getTileByPair(to);

        if (fromTile.getPiece() == null) {
            answer = false;
        } else if (fromTile.getPiece().getColor() != moveColor) {
            answer = false;
        } else if (fromTile.pieceColorEquals(toTile)) {
            answer = false;
        } else if (moveOpensKingForCheck(from, to)) {
            answer = false;
        } else if (isCastling(from, to)) {
            answer = true;
        } else {
            Piece fromPiece = fromTile.getPiece();
            int[] vector = fromPiece.vectorOf(from, to);
            boolean onTake = (toTile.getPiece() != null);
            if (!fromPiece.moveIsObtainable(vector, onTake)) {
                answer = false;
            } else if (!board.roadClear(from, to)) {
                answer = false;
            } else {
                answer = true;
            }
        }

        return answer;
    }

/*    public boolean isEnPassant(Pair from, Pair to) {
        boolean answer = false;
        Piece fromPiece = board.getPieceByPair(from);
        Tile toTile = board.getTileByPair(to);
        int[] vector = fromPiece.vectorOf(from, to);

        if (fromPiece instanceof Pawn
                && toTile.isEnPassant()
                && fromPiece.moveIsObtainable(vector, true)) {
            answer = true;
        }

        return answer;
    }

    public void doublePawnMoveCausesEnPassant(Pair from, Pair to) {
        fromPiece = board.getPieceByPair(from);
        if (fromPiece instanceof Pawn
                && Math.abs(to.i() - from.i()) == 2
                && to.j() == from.j()) {
            Pair middle = new Pair(from.i() +
                ((to.i() - from.i())/2), from.j());
            Tile middleTile = getTileByPair(middle);
            middleTile.setEnPassantTarget(to);
        }
    }

    public void enPassant(Pair from, Pair to) {
        Tile fromTile = board.getTileByPair(from);
        Tile toTile = board.getTileByPair(to);
        board.transfer(fromTile, toTile);
        changeSide();
        lookForCheck();
        checkAvailableMoves();
    }*/

    public boolean notEnd() {return !gameOver;}

    private void changeSide() {
        if (moveColor == Color.WHITE) {
            moveColor = Color.BLACK;
        } else {
            moveColor = Color.WHITE;
        }
    }

    public String reason() {
        String answer;

        if (isCheck == true) {
            answer = "Mate!";
        } else {
            answer = "Pat!";
        }

        return answer;
    }
    
    /*public boolean isEnPassant(Pair from, Pair to) {
        boolean answer = false;
        Piece fromPiece = board.getPieceByPair(from);

        if ((to.j() == from.j())
                && (fromPiece instanceof Pawn)
                && (fromPiece.getFirstMove())
                && (Math.abs(to.j() - from.j()) == 2)) { 
            answer = true;
        } else {
            answer = false;
        }

        return answer;
    }*/

    public boolean isCastling(Pair from, Pair to) {
        boolean answer = false;
        Piece fromPiece = board.getPieceByPair(from);

        if ((to.i() == from.i())
                && (fromPiece instanceof King)
                && (fromPiece.getFirstMove())
                && (Math.abs(to.j() - from.j()) == 2)) { 
            answer = true;
        } else {
            answer = false;
        }

        return answer;
    }

    public boolean tileUnderAttack(Tile tile, Color enemyColor) {
        boolean answer = false;
        TileSet arr = board.getTileSet(enemyColor);
        Tile enemyTile;
        Piece enemyPiece;

        for (int i = 0; i < arr.size(); i++) {
            enemyTile = arr.get(i);
            enemyPiece = enemyTile.getPiece();
            Pair from = enemyTile.getPair();
            Pair to = tile.getPair();
            int[] vector = enemyPiece.vectorOf(from, to);
            boolean onTake = false;

            if (enemyPiece.moveIsObtainable(vector, onTake)
                    && board.roadClear(from, to)) {
                answer = true;
                break;
            }
        }

        return answer;
    }

    public boolean tileUnderAttack(Pair pair, Color enemyColor) {
        return tileUnderAttack(board.getTileByPair(pair), enemyColor);
    }

    public boolean roadUnderAttack(Pair from, Pair to) {
        boolean answer = false;
        Piece fromPiece = board.getPieceByPair(from);
        int i = to.i() - from.i(), j = to.j() - from.j();
        Pair unitMovePair = new Pair((int) Math.signum(i), 
                (int) Math.signum(j));
        Pair step = new Pair(from.i(), from.j());

        while (!step.equals(to)) {
            step = step.add(unitMovePair);
            if (tileUnderAttack(step, fromPiece.getOpponentsColor())
                    && !step.equals(to)) {
                answer = true;
                break;
            }
        }

        return answer;
    }

    public void castling(Pair fromKingPair, Pair toKingPair) throws WrongMoveExeption {
        Pair fromRookPair, toRookPair;

        if (toKingPair.j() - fromKingPair.j() == 2) { 
            fromRookPair = new Pair(fromKingPair.i(), fromKingPair.j() + 3);
            toRookPair = new Pair(fromKingPair.i(), fromKingPair.j() + 1);
        } else if (toKingPair.j() - fromKingPair.j() == -2) {
            fromRookPair = new Pair(fromKingPair.i(), fromKingPair.j() - 4);
            toRookPair = new Pair(fromKingPair.i(), fromKingPair.j() - 1);
        } else {
            throw new WrongMoveExeption("That piece moves differently!");
        }
        
        Piece rook = board.getPieceByPair(fromRookPair);

        if (rook == null) {
            throw new WrongMoveExeption("No rook for castling!");
        } else if (!rook.getFirstMove()) {
            throw new WrongMoveExeption("Rook had been moved!");
        } else if (!board.roadClear(fromKingPair, fromRookPair)) {
            throw new WrongMoveExeption("Obstacle on road!");
        } else if (roadUnderAttack(fromKingPair, fromRookPair)) {
            throw new WrongMoveExeption("Road is under attack!");
        } else {
            board.transfer(fromKingPair, toKingPair);
            board.transfer(fromRookPair, toRookPair);
        }
    }

    public boolean moveOpensKingForCheck(Pair from, Pair to) {
        boolean answer = false;
        Tile toTile = board.getTileByPair(to);
        Piece toPiece = toTile.getPiece();
        int toIndex = -1;
        if (toPiece != null) {
            TileSet toArr = board.getTileSet(toPiece.getColor());
            toIndex = toArr.indexOf(toTile);
        }

        board.transfer(from, to);

        Tile kingsTile;

        kingsTile = board.getKingsTile(moveColor);
        Piece king = kingsTile.getPiece();

        if (tileUnderAttack(kingsTile, king.getOpponentsColor())) {
            answer = true;
        }

        board.antiTransfer(from, to, toPiece, toIndex);

        return answer;
    }

    public void lookForCheck() {
        Color enemyColor;

        if (moveColor == Color.WHITE) {
            enemyColor = Color.BLACK;
        } else {
            enemyColor = Color.WHITE;
        }

        Tile kingsTile = board.getKingsTile(moveColor);

        isCheck = tileUnderAttack(kingsTile, enemyColor);
        if (isCheck) {
            System.out.println("Check!");
        }
    }

    public void checkAvailableMoves() {
        boolean isMate = true;
        TileSet arr = board.getTileSet(moveColor);
        Pair from, to;
        boolean stop = false;

        for (int i = 0; i < arr.size() && !stop; i++) {
            from = arr.get(i).getPair();
            for (int j = 0; j < 8 && !stop; j++) {
                for (int k = 0; k < 8 && !stop; k++) {
                    to = new Pair(j, k);
                    if (goodMove(from, to)) {
                        if (isCheck) {
                            System.out.println("from [" + from.i() + ", " + from.j() + "]");
                            System.out.println("to [" + j + ", " + k + "]");
                        }
                        isMate = false;
                        stop = true;
                    }
                }
            }
        }

        gameOver = isMate;
    }
}
