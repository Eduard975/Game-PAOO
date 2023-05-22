package Entity;

import GameWindow.Game;

public class Projectile extends Entity {

    public Projectile(Game gp) {
        super(gp);
    }

    public void set(String direction, int x, int y, int atk) {
        this.direction = direction;
        this.x = x;
        this.y = y;
        this.attack = atk;

    }

    public void update() {
        int enem_indx = gp.colCheck.checkEntity(this, false);
        if (enem_indx != 999) {
            gp.enemies[enem_indx].damage(attack);
            if (gp.enemies[enem_indx].hp <= 0) {
                gp.enemies[enem_indx] = null;
            }
            hp = 0;
        } else {
            switch (direction) {
                case "up" -> y -= speed;
                case "down" -> y += speed;
                case "left" -> x -= speed;
                case "right" -> x += speed;
            }
            hp--;
        }
    }

}
