package game;

import pieces.Piece;

import java.awt.*;
import java.util.ArrayList;

public class Setup {

    GameLoop gl;
    public static ArrayList<Piece> pieces = new ArrayList<>();
    public static ArrayList<Piece> simPieces = new ArrayList<>();

    // Resets the board when you type "reset" in console using a sort.

    // calls setup white and black

    public Setup(GameLoop gl){
        this.gl = gl;
    }

    public void init(){
        SetupWhite white = new SetupWhite(gl);
        SetupBlack black = new SetupBlack(gl);

        pieces.addAll(SetupWhite.pieces);
        pieces.addAll(SetupBlack.pieces);
        copyPieces(pieces, simPieces);
    }

    public void reset() {

    }

    public void draw(Graphics2D g2) {
        for (Piece p : Setup.simPieces){
            p.draw(g2, gl);
        }
    }

    // CHANGE THIS TO STACK
    private void copyPieces (ArrayList<Piece> from, ArrayList<Piece> to){
        to.clear();
        to.addAll(from);
    }
}
