package Map;

import GameWindow.Game;
import Graphics.Assets;

import java.awt.*;
import java.io.File;
import java.util.Scanner;

public class TileManager {
    Game gp;
    Tile[] tiles;
    public int[][] map;

    public TileManager(Game gp) {
        this.gp = gp;
        tiles = new Tile[3];
        map = new int[gp.getMaxWorldCol()][gp.getMaxWorldRow()];
        generateTiles();
        loadMap("src/Resources/Maps/map.txt");
    }

    public void loadMap(String path) {
        try {
            Scanner fin = new Scanner(new File(path));

            int col = 0;
            int row = 0;

            while (col < gp.getMaxWorldCol() && row < gp.getMaxWorldRow() && fin.hasNextInt()) {
                map[col][row] = fin.nextInt();
                col++;
                if (col == gp.getMaxWorldCol()) {
                    col = 0;
                    row++;
                }
            }

            fin.close();
        } catch (Exception e) {
            throw new RuntimeException("Eroare citire fisier");
        }
    }

    public void generateTiles() {
        Assets.Init();

        for (int i = 0; i < 3; i++) {
            tiles[i] = new Tile();
            switch (i) {
                case 0 -> tiles[i].image = Assets.grass;
                case 1 -> tiles[i].image = Assets.soil;
                case 2 -> {
                    tiles[i].image = Assets.water;
                    tiles[i].collision = true;
                }
            }
        }
    }

    public void draw(Graphics2D g2D) {
        int world_col = 0, world_row = 0;
        //Camera Jocului
        while (world_col < gp.getMaxWorldCol() && world_row < gp.getMaxWorldRow()) {
            int world_x = world_col * gp.getDefaultTileSize();
            int world_y = world_row * gp.getDefaultTileSize();

            int screen_x = world_x + gp.player.x - gp.player.world_x;
            int screen_y = world_y + gp.player.y - gp.player.world_y;

            if (world_x + gp.getDefaultTileSize() > gp.player.world_x - gp.player.x &&
                    world_x - gp.getDefaultTileSize() - 32 < gp.player.world_x + gp.player.x &&
                    world_y + gp.getDefaultTileSize() > gp.player.world_y - gp.player.y &&
                    world_y - gp.getDefaultTileSize() - 49 < gp.player.world_y + gp.player.y
            ) {
                int tileType = map[world_col][world_row];
                g2D.drawImage(tiles[tileType].image, screen_x, screen_y, 48, 48, null);
            }

            world_col++;

            if (world_col == gp.getMaxWorldCol()) {
                world_col = 0;
                world_row++;
            }
        }
    }

    public boolean getCollision(int tileNum) {
        return tiles[tileNum].collision;
    }
}
