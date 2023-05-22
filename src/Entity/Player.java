package Entity;

import GameWindow.Game;
import Graphics.PlayerAssets;
import Input.KeyManager;

import java.awt.*;
import java.util.Objects;

public class Player extends Entity {
    KeyManager keyM;
    private static Player player = null;
    public int texture_option;

    int kill_count = 0;

    private Player(Game gp, KeyManager keyM) {
        super(gp);
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

        kill_count = 0;

        hitBox.x = 25;
        hitBox.y = 22;
        hitBox.width = 20;
        hitBox.height = 54;

        original_hitbox_x = hitBox.x;
        original_hitbox_y = hitBox.y;

        hp = 100;
        attack = 20;
        speed = 4;
        direction = "right";
        texture_option = 0;
    }

    public void update() {
        is_collided = false;
        gp.colCheck.checkTile(this);


        int Obj_index = gp.colCheck.checkObject(this, true);
        int enemy_index = gp.colCheck.checkEntity(this, true);
        pickupObject(Obj_index);
        touchedEnemy(enemy_index);

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

    public void touchedEnemy(int index) {
        if (index != 999) {
            String enemName = gp.enemies[index].name;
            if (Objects.equals(enemName, "C")) {
                gp.enemies[index] = null;
                kill_count++;
                hp += -10;
            }
        }
    }

    @Override
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

    public int getKD() {
        return kill_count;
    }

    public int getHp() {
        return hp;
    }
}