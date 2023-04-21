package Entity;

import GameWindow.Game;
import Graphics.PlayerAssets;
import Input.KeyManager;

import java.awt.*;

public class Player extends Entity {
    Game gp;
    KeyManager keyM;
    private static Player player = null;
    public int texture_option;

    private Player(Game gp, KeyManager keyM) {
        this.gp = gp;
        this.keyM = keyM;

        setDefaultValues();
    }

    public static Player getInstance(Game gp, KeyManager keyM) {
        if (player == null) {
            player = new Player(gp, keyM);
        }

        return player;
    }

    public void setDefaultValues() {
        x = gp.getScreenWidth() / 2;
        y = gp.getScreenHeight() / 2;

        world_x = gp.getDefaultTileSize() * gp.getMaxWorldCol() / 2 - 32;
        world_y = gp.getDefaultTileSize() * gp.getMaxWorldRow() / 2 - 49;

        hitBox = new Rectangle(25, 22, 20, 54);

        speed = 4;
        direction = "right";
        texture_option = 0;
    }

    public void update() {
        is_collided = false;
        gp.colCheck.checkTile(this);

        if (!is_collided) {
            if (keyM.W) {
                direction = "up";
                world_y -= speed;
            }

            if (keyM.S) {
                direction = "down";
                world_y += speed;
            }

            if (keyM.A) {
                direction = "left";
                world_x -= speed;
            }

            if (keyM.D) {
                direction = "right";
                world_x += speed;
            }
        } else {
            if (keyM.W) {
                direction = "up";
            }

            if (keyM.S) {
                direction = "down";
            }

            if (keyM.A) {
                direction = "left";
            }

            if (keyM.D) {
                direction = "right";
            }
        }

    }

    public void draw(Graphics2D g2D) {
        PlayerAssets.Init(texture_option);

        switch (direction) {
            case "left" -> texture = PlayerAssets.heroLeft;
            case "right" -> texture = PlayerAssets.heroRight;
        }

        g2D.drawImage(texture, x, y, 64, 98, null);
    }
}