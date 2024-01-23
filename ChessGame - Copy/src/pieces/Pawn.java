package pieces;

import game.GameLoop;

public class Pawn extends Piece {

    public Pawn (int color, int x, int y) {
        super (color, x, y, 1);

        if (color == GameLoop.WHITE) image = getImg( "pawnW");
        if (color == GameLoop.BLACK) image = getImg( "pawnB");

    }

}
