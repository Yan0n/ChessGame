package game;

import pieces.*;
import java.util.ArrayList;

import static game.GameLoop.BLACK;
public class SetupBlack {
    GameLoop gl;
    protected static ArrayList<Piece> pieces = new ArrayList<>();
    public SetupBlack(GameLoop gl) {
        this.gl = gl;

        init();
    }
    public void init(){
        pieces.add(new Pawn(BLACK, findTile(0), findTile(1)));
        pieces.add(new Pawn(BLACK, findTile(1), findTile(1)));
        pieces.add(new Pawn(BLACK, findTile(2), findTile(1)));
        pieces.add(new Pawn(BLACK, findTile(3), findTile(1)));
        pieces.add(new Pawn(BLACK, findTile(4), findTile(1)));
        pieces.add(new Pawn(BLACK, findTile(5), findTile(1)));
        pieces.add(new Pawn(BLACK, findTile(6), findTile(1)));
        pieces.add(new Pawn(BLACK, findTile(7), findTile(1))); // change this to 1
        pieces.add(new Rook(BLACK, findTile(0), findTile(0)));
        pieces.add(new Knight(BLACK, findTile(1), findTile(0)));
        pieces.add(new Bishop(BLACK, findTile(2), findTile(0)));
        pieces.add(new Queen(BLACK, findTile(3), findTile(0)));
        pieces.add(new King(BLACK, findTile(4), findTile(0)));
        pieces.add(new Bishop(BLACK, findTile(5), findTile(0)));
        pieces.add(new Knight(BLACK, findTile(6), findTile(0)));
        pieces.add(new Rook(BLACK, findTile(7), findTile(0)));

    }

    private int findTile (int x){
        return gl.tileSize * x;
    }



}
