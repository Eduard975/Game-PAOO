package Objects;

import GameWindow.Game;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SuperObject {
    public BufferedImage image;
    public String name;
    public Rectangle hitBox = new Rectangle(0, 0, 48, 48);
    public int original_hitbox_x = 0, original_hitbox_y = 0;
    public boolean isSolid = false;
    public int x, y;

    public void setCoords(int col, int row) {
        x = col * 48;
        y = row * 48;
    }

    public void draw(Graphics2D g2D, Game gp) {
        int screen_x = x + gp.player.x - gp.player.world_x;
        int screen_y = y + gp.player.y - gp.player.world_y;
        int tileSize = Game.getDefaultTileSize_s();
        if (x + tileSize > gp.player.world_x - gp.player.x &&
                x - tileSize - 32 < gp.player.world_x + gp.player.x &&
                y + tileSize > gp.player.world_y - gp.player.y &&
                y - tileSize - 49 < gp.player.world_y + gp.player.y
        ) {
            g2D.drawImage(image, screen_x, screen_y, 48, 48, null);

        }

    }
}
