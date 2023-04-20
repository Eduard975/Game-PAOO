package Map;

import GameWindow.Game;
import Graphics.Assets;

import java.awt.*;
import java.util.Random;
public class TileManager {
    Game gp;
    Tile[] tiles;

    public TileManager(Game gp){
        this.gp = gp;
        tiles =  new Tile[3];
        generateTiles();
    }

    public void generateTiles(){
        Assets.Init();

        for(int i = 0; i<3; i++){
            tiles[i] =  new Tile();
            switch (i){
                case 0 -> tiles[i].image = Assets.grass;
                case 1 -> tiles[i].image = Assets.soil;
                case 2 -> tiles[i].image = Assets.water;
            }
        }
    }

    public void draw(Graphics2D g2D){
        int col = 0, row = 0, x = 0, y = 0;
        while(col < gp.getMaxScreenCol() && row < gp.getMaxScreenRow()){
            if(col == 12 || row == 7)
                g2D.drawImage(tiles[1].image, x ,y, 48, 48, null);
            else
                g2D.drawImage(tiles[0].image, x ,y, 48, 48, null);
            col++;
            x += 48;

            if(col == gp.getMaxScreenCol()){
                x = 0;
                col = 0;
                y += 48;
                row++;
            }
        }
    }
}
