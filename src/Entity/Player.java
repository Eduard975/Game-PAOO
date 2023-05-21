package Entity;

import GameWindow.Game;
import Graphics.PlayerAssets;
import Input.KeyManager;

import java.awt.*;
import java.util.Objects;

public class Player extends Entity {
    Game gp;
    KeyManager keyM;
    private static Player player = null;
    public int texture_option;

    int hp = 100;

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

        world_x = Game.getDefaultTileSize_s() * gp.getMaxWorldCol() / 2 - 32;
        world_y = Game.getDefaultTileSize_s() * gp.getMaxWorldRow() / 2 - 80;

        hitBox = new Rectangle(25, 22, 20, 54);

        original_hitbox_x = hitBox.x;
        original_hitbox_y = hitBox.y;

        speed = 4;
        direction = "right";
        texture_option = 0;
    }

    public void update() {
        is_collided = false;
        gp.colCheck.checkTile(this);

        int Obj_index = gp.colCheck.checkObject(this, true);
        pickupObject(Obj_index);

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

    public void pickupObject(int index) {
        if (index != 999) {
            String objName = gp.obj[index].name;
            if (Objects.equals(objName, "keyBoard")) {
                gp.obj[index] = null;
                System.out.println("Old speed: " + speed);
                speed += 2;
                System.out.println("New speed: " + speed);
                System.out.println("------------------------------------");
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

    public void setTexture_option(int texture_option) {
        this.texture_option = texture_option;
    }

    public void updateCoords() {
        x = gp.getScreenWidth() / 2;
        y = gp.getScreenHeight() / 2;

        world_x = Game.getDefaultTileSize_s() * gp.getMaxWorldCol() / 2 - 32;
        world_y = Game.getDefaultTileSize_s() * gp.getMaxWorldRow() / 2 - 80;
    }

    public void setHp(int x) {
        hp = x;
    }

    public int getHp() {
        return hp;
    }
}