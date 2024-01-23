package pieces;

import game.GameLoop;

public class Knight extends Piece {

    public Knight(int color, int x, int y) {
        super (color, x, y, 3);

        if (color == GameLoop.WHITE) image = getImg("knightW");
        if (color == GameLoop.BLACK) image = getImg( "knightB");
    }

}
