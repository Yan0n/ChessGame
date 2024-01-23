package pieces;

import game.GameLoop;

import java.lang.reflect.Array;

public class Bishop extends Piece {

    public Bishop(int color, int x, int y) {

        super (color, x, y, 4);

        if (color == GameLoop.WHITE) image = getImg( "bishopW");

        if (color == GameLoop.BLACK) image = getImg("bishopB");

    }

}
