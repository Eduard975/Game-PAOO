package Entity;

import GameWindow.Game;

public class Projectile extends Entity {

    public Projectile(Game gp) {
        super(gp);
    }

    public void set(String direction, int x, int y, int atk, int hp) {
        this.direction = direction;
        this.x = x;
        this.y = y;
        this.hitBox.x = x;
        this.hitBox.y = y;
        this.original_hitbox_x = x;
        this.original_hitbox_y = y;
        this.attack = atk;
        this.hp = hp;

    }

    public void update() {
        int enemyindex = gp.colCheck.checkEntity(this, gp.enemies);
        if (enemyindex != 999) {
            gp.enemies[enemyindex].damage(this.attack);
            if (gp.enemies[enemyindex].hp <= 0) {
                gp.enemies[enemyindex] = null;
                used = true;
                gp.player.incKillC();
            }
        }

        if (!used) {
            switch (direction) {
                case "up" -> y += speed;
                case "down" -> y -= speed;
                case "left" -> x += speed;
                case "right" -> x -= speed;
            }
            hp--;
        }
    }
}
