package Entity;

import GameWindow.Game;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {
    Game gp;
    public int x, y;
    public int world_x, world_y;
    public int speed;
    public int attack;
    public int hp;
    public String name;
    public Rectangle hitBox = new Rectangle();
    public int original_hitbox_x, original_hitbox_y;
    public boolean is_collided = false;
    public BufferedImage texture;
    public String direction;

    public Entity(Game gp) {
        this.gp = gp;
    }

    public void update() {
        int player_x = gp.player.world_x;
        int player_y = gp.player.world_y;

        if (player_x != x || player_y != y) {
            if (player_y > y) {
                y += speed;
            } else {
                y -= speed;
            }

            if (player_x > x) {
                x += speed;
            } else {
                x -= speed;
            }
        }
    }

    public void draw(Graphics2D g2D) {
        int screen_x = x + gp.player.x - gp.player.world_x;
        int screen_y = y + gp.player.y - gp.player.world_y;
        int tileSize = Game.getDefaultTileSize_s();
        if (x + tileSize > gp.player.world_x - gp.player.x &&
                x - tileSize - 32 < gp.player.world_x + gp.player.x &&
                y + tileSize > gp.player.world_y - gp.player.y &&
                y - tileSize - 32 < gp.player.world_y + gp.player.y
        ) {
            g2D.drawImage(texture, screen_x, screen_y, 64, 64, null);

        }

    }
}