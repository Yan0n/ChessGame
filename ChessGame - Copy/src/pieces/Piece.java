package pieces;

import abstractDataTypes.Stack;
import game.GameLoop;
import game.Setup;


import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import static game.Setup.simPieces;

// Main class for piece, this will have
public class Piece {
    private static int initMove = 0;
    private static int checkerD = 0;
    GameLoop gl;
    private  int x, y;

    public Piece pieceHit;
    public BufferedImage image;

    public int color;

    public int ID;

    public Piece(int color, int x, int y, int ID) {
        this.color = color;
        this.x = x;
        this.y = y;
        this.ID = ID;
    }

    //  ADD PAWN ATTACK "Diagonal" I probably wont implement this by tomorrow

    public Piece getPieceHit (int targetX, int targetY){
        for (Piece piece : simPieces){
            if (piece.getX() == targetX && piece.getY() == targetY && piece != this){
                return piece;
            }
        }
        return null;
    }

    public boolean validSquare (int targetX, int targetY){
        pieceHit = getPieceHit(targetX, targetY);

        if (pieceHit == null) return true; // not hitting any piece

        else{
            if (pieceHit.getColor() != this.getColor()){
                return true;
            }
            else {
                pieceHit = null;
            }

        }

        return false;

    }
    private Stack diagColl(int x, int y, GameLoop gl) {

        Stack test = new Stack();

        /*
        Tabulate, put values in stacks, then sort them.
         */
            for (int i = gl.tileSize; border(x + i, y + i, gl.tileSize); i+= gl.tileSize) {

                int destX = x + i;
                int destY = y + i;

                if (validSquare(destX, destY)) {
                    // Highlight move (ADD TO STACK
                    test.push(destY);
                    test.push(destX);
                    // again, x,y,x,y format :)
                } else {
                    // Stop if there's a piece in the way
                    break;
                }
            }

        for (int i = gl.tileSize; border(x - i, y + i, gl.tileSize); i+= gl.tileSize) {

            int destX = x - i;
            int destY = y + i;

            if (validSquare(destX, destY)) {
                // Highlight move (ADD TO STACK
                test.push(destY);
                test.push(destX);
                // again, x,y,x,y format :)
            } else {
                // Stop if there's a piece in the way
                break;
            }
        }

        for (int i = gl.tileSize; border(x + i, y - i, gl.tileSize); i+= gl.tileSize) {

            int destX = x + i;
            int destY = y - i;

            if (validSquare(destX, destY)) {
                // Highlight move (ADD TO STACK
                test.push(destY);
                test.push(destX);
                // again, x,y,x,y format :)
            } else {
                // Stop if there's a piece in the way
                break;
            }
        }

        for (int i = gl.tileSize; border(x - i, y - i, gl.tileSize); i+= gl.tileSize) {

            int destX = x - i;
            int destY = y - i;

            if (validSquare(destX, destY)) {
                // Highlight move (ADD TO STACK
                test.push(destY);
                test.push(destX);
                // again, x,y,x,y format :)
                // THIS WAS THE PROBLEM
                // I needed to put it into y,x,y,x format ****
            } else {
                // Stop if there's a piece in the way
                break;
            }
        }


        return test;
    }
    private Stack latColl(int x, int y, GameLoop gl) {

        Stack test = new Stack();

        /*
        Tabulate, put values in stacks, then sort them.
         */

        for (int i = gl.tileSize; border(x + i, y, gl.tileSize); i+= gl.tileSize) {

            int destX = x + i;

            if (validSquare(destX, y)) {
                // Highlight move (ADD TO STACK
                test.push(y);
                test.push(destX);
            } else {
                // Stop if there's a piece in the way
                break;
            }
        }

        for (int i = gl.tileSize; border(x - i, y, gl.tileSize); i+= gl.tileSize) {

            int destX = x - i;

            if (validSquare(destX, y)) {
                // Highlight move (ADD TO STACK
                test.push(y);
                test.push(destX);
            } else {
                // Stop if there's a piece in the way
                break;
            }
        }

        for (int i = gl.tileSize; border(x, y + i, gl.tileSize); i+= gl.tileSize) {

            int destY = y + i;

            if (validSquare(x, destY)) {
                // Highlight move (ADD TO STACK
                test.push(destY);
                test.push(x);
            } else {
                // Stop if there's a piece in the way
                break;
            }
        }

        for (int i = gl.tileSize; border(x, y - i, gl.tileSize); i+= gl.tileSize) {

            int destY = y - i;

            if (validSquare(x, destY)) {
                // Highlight move (ADD TO STACK
                test.push(destY);
                test.push(x);
                // again, x,y,x,y format :)
                // THIS WAS THE PROBLEM
                // I needed to put it into y,x,y,x format ****
            } else {
                // Stop if there's a piece in the way
                break;
            }
        }


        return test;
    }
    private Stack queenMove (int x, int y, GameLoop gl) {
        Stack test = new Stack();
        Stack testF = new Stack();

        Stack test1 = diagColl(x,y,gl);
        Stack test2 = latColl(x,y,gl);

        while (!test1.isEmpty()){
            test.push(test1.pop());
        }
        while (!test2.isEmpty()){
            test.push(test2.pop());
        }

        while (!test.isEmpty()){
            testF.push(test.pop());
        }

        return testF;
    }
    private Stack knightMove (int x, int y, GameLoop gl) {

        Stack test = new Stack();

        int[] dx = {-2, -2, -1, -1, 1, 1, 2, 2};
        int[] dy = {-1, 1, -2, 2, -2, 2, -1, 1};

        for (int i = 0; i < 8; i++) {
            int destX = x + dx[i] * gl.tileSize;
            int destY = y + dy[i] * gl.tileSize;

            if (border(destX, destY, gl.tileSize) && validSquare(destX, destY)) {
                checkerD = 0;
                test.push(destY);
                test.push(destX);
            }
        }

        return test;
    }
    private Stack pawnMove (int x, int y, GameLoop gl) {

        boolean start = (initMove < 1);

        Stack test = new Stack();

//        int[] dx = {-1, 1};
        int[] dy;

        if (color == GameLoop.WHITE) dy = new int[]{-2, -1};
        else dy = new int[]{1, 2};
        int destY;
        destY = y + dy[0] * gl.tileSize;
        if (border(x, destY, gl.tileSize) && validSquare(x, destY)) {
            test.push(destY);
            test.push(x);
            if (start){
                destY = y + dy[1] * gl.tileSize;
                if (border(x, destY, gl.tileSize) && validSquare(x, destY)) {
                    test.push(destY);
                    test.push(x);
                }
            }

        }

        return test;
    }
    private Stack kingMove (int x, int y, GameLoop gl) {

        Stack test = new Stack();

        int[] d = {-1, 0, 1};

        for (int i = 0; i < d.length; i++) {
            for (int j = 0; j < d.length; j++) {

                boolean mainSq = true;

                int destX = x + d[i] * gl.tileSize;
                int destY = y + d[j] * gl.tileSize;

                if (d[i] == 0 && d[j] == 0) mainSq = false;

                if (border(destX, destY, gl.tileSize) && validSquare(destX, destY) && mainSq) {
                    checkerD = 0;
                    test.push(destY);
                    test.push(destX);
                }
            }
        }

        return test;
    }
    public Stack pieceColl (int ID, int x, int y, GameLoop gl){
        if (ID == 1) return pawnMove(x,y,gl); // Pawn

        if (ID == 3) return knightMove(x,y,gl); // Knight

        if (ID == 4) return diagColl(x,y,gl); // Bishop

        if (ID == 5) return latColl(x,y,gl); // Rook

        if (ID == 9) return queenMove(x,y,gl); // Queen

        if (ID == 10) return kingMove(x,y,gl); // King // Not enough time to add check, checkmate, etc.

        else return null;

    }

    public BufferedImage getImg(String name){
        try {
            return image = ImageIO.read(getClass().getResourceAsStream("/assets/pieces/" + name + ".png"));
        } catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }

    private boolean border (int xC, int yC, int one) {
        boolean yWithinBoard = (yC < 8 * one && yC >= 0);
        boolean xWithinBoard = (xC < 8 * one && xC >= 0);
        return yWithinBoard && xWithinBoard;

    }

    public void draw (Graphics2D g2, GameLoop gl) {
        g2.drawImage(image, x, y, gl.tileSize, gl.tileSize, null);

    }

    // for the pieces, they are displayed from the top left corner coord.
    // in this case, I want to set the initial x and y for the tiles that
    // I want to display.

    // GETTERS AND SETTERS FOR COORDS

    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public int  getColor () {
       return color;
    }

    public int getIndex () {
        for (int i = 0; i < simPieces.size(); i++){
            if (simPieces.get(i) == this){
                return i;
            }
        }
        return 0;
    }

    public int getID() {
        return ID;
    }

    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }


}
