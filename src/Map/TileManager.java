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
        int world_col = 0, world_row = 0;

        //Camera Jocului
        while(world_col < gp.getMaxWorldCol() && world_row < gp.getMaxWorldRow()){
            int world_x = world_col * gp.getDefaultTileSize();
            int world_y = world_row * gp.getDefaultTileSize();

            int screen_x = world_x + gp.player.x - gp.player.world_x;
            int screen_y = world_y + gp.player.y - gp.player.world_y;


            if(     world_x + gp.getDefaultTileSize() > gp.player.world_x - gp.player.x  &&
                    world_x - gp.getDefaultTileSize() - 32 < gp.player.world_x + gp.player.x  &&
                    world_y + gp.getDefaultTileSize() > gp.player.world_y - gp.player.y  &&
                    world_y - gp.getDefaultTileSize() - 49 < gp.player.world_y + gp.player.y
            ) {
                g2D.drawImage(tiles[0].image, screen_x, screen_y, 48, 48, null);
            }

            world_col++;

            if(world_col == gp.getMaxWorldCol()){
                world_col = 0;
                world_row++;
            }
        }
    }
}
