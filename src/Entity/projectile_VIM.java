package Entity;

import GameWindow.Game;
import Graphics.ItemAssets;

import static java.lang.Math.sqrt;

public class projectile_VIM extends Entity {
    double CD = 2;

    public projectile_VIM(Game gp) {
        super(gp);
        speed = 15;
        attack = 10;
        hp = 60 * 5;
        int size = (int) (32 * sqrt(2));

        hitBox.x = 32 - size;
        hitBox.y = 32 - size;
        hitBox.width = size;
        hitBox.height = size;
        

        ItemAssets.Init();
        texture = ItemAssets.vim;

        direction = "right";
    }

    public void update() {
        switch (direction) {
            case "up" -> y -= speed;
            case "down" -> y += speed;
            case "left" -> x -= speed;
            case "right" -> x += speed;
        }
    }
}
