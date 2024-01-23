package pieces;

import game.GameLoop;


public class King extends Piece {

    public King(int color, int x, int y) {
        super (color, x, y, 10);

        if (color == GameLoop.WHITE) image = getImg("kingW");
        if (color == GameLoop.BLACK) image = getImg( "kingB");
    }



    public static boolean kingMoves(int x, int y, int xMove, int yMove, GameLoop gl){

        int xDif = (x - xMove);
        int yDif = (y - yMove);

        boolean caseA = !(x == xMove && y == yMove); // default case

        boolean caseB = (xDif>=-(gl.tileSize) && xDif<=(gl.tileSize)); // Left Right case

        boolean caseC = (yDif>=-(gl.tileSize) && yDif<=(gl.tileSize)); // Up Down case

        // =  +1  =
        // -1  K +1
        // =  -1  =

        return caseA && caseB && caseC;
    }

}
