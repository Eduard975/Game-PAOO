package Map;

import GameWindow.Game;
import Graphics.Assets;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TileManager {
    Game gp;
    Tile[] tiles;
    public int[][] map;
    String path = "src/Resources/Maps/map.txt";
    int map_option = 0;

    public TileManager(Game gp) {
        this.gp = gp;
        tiles = new Tile[4];

        try {
            Scanner fin = new Scanner(new File(path));
            int row = fin.nextInt();
            int col = fin.nextInt();

            gp.setMaxWorldRow(row);

            gp.setMaxWorldCol(col);
            map = new int[row][col];
            System.out.println(row + " " + col);
            fin.close();

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        generateTiles();
        loadMap(path);
    }

    public void loadMap(String path) {
        try {
            Scanner fin = new Scanner(new File(path));
            int row = fin.nextInt(); //ignor primele 2 valori din fisier
            int col = fin.nextInt();

            for (row = 0; row < gp.getMaxWorldRow(); row++)
                for (col = 0; col < gp.getMaxWorldCol(); col++)
                    if (fin.hasNextInt()) {
                        map[row][col] = fin.nextInt();
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
            int world_x = world_col * Game.getDefaultTileSize_s();
            int world_y = world_row * Game.getDefaultTileSize_s();

            int screen_x = world_x + gp.player.x - gp.player.world_x;
            int screen_y = world_y + gp.player.y - gp.player.world_y;

            if (world_x + Game.getDefaultTileSize_s() > gp.player.world_x - gp.player.x &&
                    world_x - Game.getDefaultTileSize_s() - 32 < gp.player.world_x + gp.player.x &&
                    world_y + Game.getDefaultTileSize_s() > gp.player.world_y - gp.player.y &&
                    world_y - Game.getDefaultTileSize_s() - 49 < gp.player.world_y + gp.player.y
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

    public void changeMap(int map_option) {
        if (map_option != this.map_option) {
            map = null;
            switch (map_option) {
                case 0 -> {
                    path = "src/Resources/Maps/map2.txt";
                }
                case 1 -> {
                    path = "src/Resources/Maps/map3.txt";
                }
                case 2 -> {
                    path = "src/Resources/Maps/map4.txt";
                }
            }

            this.map_option = map_option;

            try {
                Scanner fin = new Scanner(new File(path));
                int row = fin.nextInt();
                int col = fin.nextInt();

                gp.setMaxWorldRow(row);
                gp.setMaxWorldCol(col);

                map = new int[row][col];
                System.out.println(row + " " + col);
                fin.close();

            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }

            generateTiles();
            loadMap(path);
        }
    }

    public boolean getCollision(int tileNum) {
        return tiles[tileNum].collision;
    }

}
