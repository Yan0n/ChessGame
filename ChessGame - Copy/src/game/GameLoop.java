package game;

import abstractDataTypes.Stack;
import pieces.Piece;
import tiles.Board;
import util.TileHL;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

import static game.Setup.simPieces;
import static java.lang.Thread.sleep;

public class GameLoop extends JPanel{

    // Piece ID

    // instead of doing this I can assign ids to the classes themselves, make them public
    // and then that way, I can put those values into the stack "on move" if I interact with them.

    // Board settings

    public final int scale = 5;
    public final int tileSize = 16 * scale;
    public final int boardSize = 8;

    public final int screenWidth = tileSize * boardSize; // Chess Board
    public final int screenHeight = tileSize * boardSize; // Chess Board

    public static final int WHITE = 1;
    public static final int BLACK = 0;

    public boolean yellowH;
    public boolean redH;
    public int mouseSquareX;
    public int mouseSquareY;
    public int mouseClickX;
    public int mouseClickY;

    public Stack moves;
    public Piece activePiece;


    // state 0 is black, state 1 is white. state switches after a turn
    // the state will influence which tiles u can interact with.
    // After clicking on an interactable tile, it will show the possible places
    // you can move to, indicated by a half lit

    private boolean rotate;
    private int state;

    private final MouseHandle mh = new MouseHandle(this);
    private final Board board = new Board(this);
    private final TileHL thl = new TileHL(this);
    private final Setup setup = new Setup(this);


    // Constructor
    public GameLoop(){
        // Preferences for window
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.addMouseMotionListener(mh);
        this.addMouseListener(mh);
        this.setFocusable(true);

    }
    public void reset() {
        ArrayList<Integer> pieceIDS = new ArrayList<Integer>();
            for (Piece piece : simPieces) {
                pieceIDS.add(piece.getID()); // you can change id to a different value for debugging //

            }
        sort(pieceIDS);
        // sorts the board back to the original. Using the id's from the
        // undo stack back to their initial spots and push.pop(king.id) into
        // its spot as well, using some method.
    }
    public void init() {
        state = WHITE;
        setup.init();
        reset(); // sorts the pieces in order of id (YOU CAN CHANGE THIS)
    }

    public void run() {
        while (true) {
            long startTime = System.currentTimeMillis();

            update();

            repaint();

            long elapsedTime = System.currentTimeMillis() - startTime;
            long sleepTime = Math.max(0, 1000 / 60 - elapsedTime);

            try {
                Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    private void update() {

        // Update mouse coords

        mouseClickX = mh.mouseCoordsP[0]/tileSize;
        mouseClickY = mh.mouseCoordsP[1]/tileSize;

        // mouse button pressed
        if (mh.mouseClick && mh.select) {

            // highlight yellow
            yellowH = true;
            // loops through all the active pieces
            for (Piece piece : simPieces){
                // if the color of the piece is the same as state,
                // as well as the location of the piece, you are able to move it.
                if (piece.color == state &&
                        piece.getX()/tileSize == mouseClickX &&
                        piece.getY()/tileSize == mouseClickY) {

                    redH = true;

                    thl.setPiece(piece);
                    moves = piece.pieceColl(piece.getID(), piece.getX(), piece.getY(), this);
                    activePiece = piece;
                    // makes this an active piece
                    // highlight red if there are tiles you can go to
                }
            }
        }
        else if (!mh.select){
            int mouseClickX2 = mh.mouseCoordsP[0] / tileSize;
            int mouseClickY2 = mh.mouseCoordsP[1] / tileSize;

            int x;
            int y;

            if (redH) {
                while (!moves.isEmpty()) {
                    x = moves.pop();
                    y = moves.pop();
                    if (x / tileSize == mouseClickX2 && y / tileSize == mouseClickY2) {
                        activePiece.setX(x);
                        activePiece.setY(y);

                        if (activePiece.pieceHit != null) {
                            simPieces.remove(activePiece.pieceHit.getIndex());
                        }

                        if (state == BLACK) state = WHITE;
                        else if (state == WHITE) state = BLACK;
                        rotate = true; // rotates the screen for QOL for the other player
                    }
                }
            }
        }
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;



        // Calls the board drawing method
        board.draw(g2);

        // call highlight
        if (thl != null) thl.draw(g2);


        AffineTransform originalTransform = g2.getTransform();


//        if (rotate){
//            g2.rotate(Math.PI);
//            rotate = false;
//        }


        // calls the piece drawing method
        setup.draw(g2);

        g2.setTransform(originalTransform);

        g2.dispose();
    }
    public static void sort(ArrayList<Integer> arr) {
        int n = arr.size();

        // Traverse through all array elements
        for (int i = 0; i < n - 1; i++) {
            // Last i elements are already sorted, so we don't need to check them
            for (int j = 0; j < n - i - 1; j++) {
                // Swap if the element found is greater than the next element
                if (arr.get(j) > arr.get(j + 1)) {
                    // Swap arr[j] and arr[j+1]
                    int temp = arr.get(j);
                    arr.set(j, arr.get(j + 1));
                    arr.set(j + 1, temp);
                }
            }
        }
        for (int i : arr) System.out.println(i);
    }
}
