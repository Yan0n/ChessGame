package game;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseHandle extends MouseAdapter {

    public boolean select;
    public boolean mouseClick;
    public int[] mouseCoordsD = new int[4];
    public int[] mouseCoordsP = new int[2];
    GameLoop gl;

    public MouseHandle(GameLoop gl){
        this.gl = gl;
    }



    @Override
    public void mousePressed(MouseEvent e) {
        mouseCoordsP[0] = e.getX();
        mouseCoordsP[1] = e.getY();
        mouseClick = true;
        select = !select;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        mouseClick = false;
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mouseCoordsD[0] = e.getX();
        mouseCoordsD[1] = e.getY();

    }
    @Override
    public void mouseDragged(MouseEvent e) {
    }
}
