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

    int CD = 60;

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

        speed = 6;
        direction = "right";
        texture_option = 0;

    }

    public void update() {
        is_collided = false;
        gp.colCheck.checkTile(this);

        int Obj_index = gp.colCheck.checkObject(this, true);
        int enemy_index = gp.colCheck.checkEntity(this, gp.enemies);
        pickupObject(Obj_index);
        touchedEnemy(enemy_index);

        if (CD == 120) {
            Projectile Bullet = new projectile_VIM(gp);
            Bullet.set(player.direction, player.world_x + 30, player.world_y + 20, player.attack, 5 * 60);
            gp.gun.add(Bullet);
            CD = 0;
        }

        CD++;

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
                speed += 2;
            }
        }
    }

    public void touchedEnemy(int index) {
        if (index != 999) {
            hp -= gp.enemies[index].attack;
            gp.enemies[index] = null;
            kill_count++;
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

    public int getKillC() {
        return kill_count;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int x) {
        hp = x;
    }

    public void incKillC() {
        kill_count++;
    }
}