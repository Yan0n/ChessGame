package tiles;

import game.GameLoop;
import util.Scale;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
public class Board {
    GameLoop gl;

    Scale scale = new Scale();

    private Tile[] tile;

    public Board(GameLoop gl){
        this.gl = gl;

        this.tile = new Tile[2];

        getTileImg();


    }

    public void getTileImg(){

        try{

            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream ("/assets/tiles/darkTile.png"));
            tile[0].image = scale.scaleImage(tile[0].image, gl.tileSize, gl.tileSize);

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream ("/assets/tiles/lightTile.png"));
            tile[1].image = scale.scaleImage(tile[1].image, gl.tileSize, gl.tileSize);

        }catch(IOException e) {
            e.printStackTrace();
        }

    }



    public void draw(Graphics2D g2) {
        int x = 0;
        int y = 0;

        for (int i=0; i<4; i++){



            for (int j=0; j<8; j++) {
                g2.drawImage(tile[1].image, x, y, null);
                x += gl.tileSize;
                g2.drawImage(tile[0].image, x, y, null);
                x += gl.tileSize;
            }

            y += gl.tileSize;
            x = 0;
            for (int j=0; j<8; j++) {
                g2.drawImage(tile[0].image, x, y, null);
                x+= gl.tileSize;
                g2.drawImage(tile[1].image, x, y, null);
                x+= gl.tileSize;
            }
            y += gl.tileSize;
            x = 0;
        }

    }
}
