package util;

import abstractDataTypes.Stack;
import game.GameLoop;
import pieces.Piece;

import java.awt.*;
import java.util.ArrayList;

public class TileHL {

    private Piece piece;

    GameLoop gl;

    public TileHL (GameLoop gl) {
        this.gl = gl;
    }
    // HIGHLIGHT TILES

    // I need one for:

    //    - Clicking on a square will light the square a yellow tint

    // - if the selected square contains a piece,
    // I want a red tint to cover the "future" moves you are able to make

    public void draw(Graphics2D g2) {
        int testX = gl.mouseClickX * gl.tileSize;
        int testY = gl.mouseClickY * gl.tileSize;

        // YELLOW HIGHLIGHT
        if (gl.yellowH) {
            g2.setColor (Color.yellow);
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.2f));
            g2.fillRect(testX, testY, gl.tileSize, gl.tileSize);
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));

        }

        if (gl.redH) {

            Stack highlight = piece.pieceColl(piece.getID(), piece.getX(), piece.getY(), gl);

            while (!highlight.isEmpty()) highLightRed(highlight.pop(), highlight.pop(), g2);

            // if u click on x,y of a red square, set new values.

                    // Turn BLUE on

//                            if (testX == stackX && testY == stack.y) gl.preBlueH = true;


                    // Checks for collision. For instance if there is another piece in the line of sight
                    // of the current piece, then the current piece shouldn't be able to move past the other
                    // piece.

                    // My solution for this is to get the x and y of the current piece
                    // as well as the x and y of "every piece whilst it loops through them"

                    // I will need to check for pieceMove inside of the piece collision class.
                    // this will make it so that it only focuses on pieces that are in the line of sight
                    // of the current piece.

                    // after checking for a pieces collision, it should only return true

                    // on second thought, it wont work with booleans.

                    // I can take this chance and get linkedlists and stacks finished

                    // I will make it return a type stack, for all the pieces.

                    // After that I can use this returned stack to fill in red
        }
        else {
            // WHITE HIGHLIGHT (small alpha diff)

            g2.setColor (Color.white);
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.2f));
            g2.fillRect(gl.mouseSquareX * gl.tileSize, gl.mouseSquareY * gl.tileSize, gl.tileSize, gl.tileSize);
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));

        }

    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public void highLightRed(int x, int y, Graphics2D g2){
        g2.setColor(Color.RED);
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.4f));
        g2.fillRect(x,y, gl.tileSize, gl.tileSize);
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));

    }

}
