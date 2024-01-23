package pieces;

import game.GameLoop;

public class Rook extends Piece {

    public Rook (int color, int x, int y) {
        super (color, x, y, 5);

        if (color == GameLoop.WHITE) image = getImg("rookW");
        if (color == GameLoop.BLACK) image = getImg("rookB");
    }

}
