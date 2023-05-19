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
        tiles = new Tile[4];
        map = new int[gp.getMaxWorldRow()][gp.getMaxWorldCol()];
        generateTiles();
        loadMap("src/Resources/Maps/map2.txt");
    }

    public void loadMap(String path) {
        try {
            Scanner fin = new Scanner(new File(path));
            System.out.println(fin.nextInt());
            int col = 0;
            int row = 0;

            for (row = 0; row < gp.getMaxWorldRow(); row++)
                for (col = 0; col < gp.getMaxWorldCol(); col++)
                    if (fin.hasNextInt()) {
                        map[row][col] = fin.nextInt();
                        System.out.println(row + " " + col);
                    }

            fin.close();
        } catch (Exception e) {
            throw new RuntimeException("Eroare citire fisier");
        }
    }

    public void generateTiles() {
        Assets.Init();

        for (int i = 0; i < tiles.length; i++) {
            tiles[i] = new Tile();
            switch (i) {
                case 0 -> tiles[i].image = Assets.grass;
                case 1 -> tiles[i].image = Assets.soil;
                case 2 -> {
                    tiles[i].image = Assets.water;
                    tiles[i].collision = true;
                }
                case 3 -> {
                    tiles[i].image = Assets.void1;
                    tiles[i].collision = true;
                }
            }
        }
    }

    public void draw(Graphics2D g2D) {
        int world_col = 0, world_row = 0;

        //Camera Jocului
        while (world_col < gp.getMaxWorldCol() && world_row < gp.getMaxWorldRow()) {
            int world_x = world_col * Game.getDefaultTileSize();
            int world_y = world_row * Game.getDefaultTileSize();

            int screen_x = world_x + gp.player.x - gp.player.world_x;
            int screen_y = world_y + gp.player.y - gp.player.world_y;

            if (world_x + Game.getDefaultTileSize() > gp.player.world_x - gp.player.x &&
                    world_x - Game.getDefaultTileSize() - 32 < gp.player.world_x + gp.player.x &&
                    world_y + Game.getDefaultTileSize() > gp.player.world_y - gp.player.y &&
                    world_y - Game.getDefaultTileSize() - 49 < gp.player.world_y + gp.player.y
            ) {
                int tileType = map[world_row][world_col];

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
