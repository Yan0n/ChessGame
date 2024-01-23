package game;

import pieces.*;
import java.util.ArrayList;

import static game.GameLoop.WHITE;
public class SetupWhite {
    GameLoop gl;

    protected static ArrayList<Piece> pieces = new ArrayList<>();

    public SetupWhite(GameLoop gl) {
        this.gl = gl;

        init();
    }
    public void init(){

        pieces.add(new Pawn(WHITE, findTile(0), findTile(6)));
        pieces.add(new Pawn(WHITE, findTile(1), findTile(6)));
        pieces.add(new Pawn(WHITE, findTile(2), findTile(6)));
        pieces.add(new Pawn(WHITE, findTile(3), findTile(6)));
        pieces.add(new Pawn(WHITE, findTile(4), findTile(6)));
        pieces.add(new Pawn(WHITE, findTile(5), findTile(6)));
        pieces.add(new Pawn(WHITE, findTile(6), findTile(6)));
        pieces.add(new Pawn(WHITE, findTile(7), findTile(6)));
        pieces.add(new Rook(WHITE, findTile(0), findTile(7)));
        pieces.add(new Knight(WHITE, findTile(1), findTile(7)));
        pieces.add(new Bishop(WHITE, findTile(2), findTile(7)));
        pieces.add(new Queen(WHITE, findTile(3), findTile(7)));
        pieces.add(new King(WHITE, findTile(4), findTile(7)));
        pieces.add(new Bishop(WHITE, findTile(5), findTile(7)));
        pieces.add(new Knight(WHITE, findTile(6), findTile(7)));
        pieces.add(new Rook(WHITE, findTile(7), findTile(7)));

    }

    private int findTile (int tile){
        return gl.tileSize * tile;
    }
}
