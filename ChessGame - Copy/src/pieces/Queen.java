package pieces;

import game.GameLoop;

public class Queen extends Piece {

    public Queen(int color, int x, int y) {
        super (color, x, y, 9);

        if (color == GameLoop.WHITE) image = getImg( "queenW");
        if (color == GameLoop.BLACK) image = getImg( "queenB");
    }

}
