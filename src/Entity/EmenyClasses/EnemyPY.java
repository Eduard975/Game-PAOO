package Entity.EmenyClasses;

import Entity.Entity;
import GameWindow.Game;
import Graphics.EnemyAssets;

public class EnemyPY extends Entity {
    public EnemyPY(Game gp) {
        super(gp);
        name = "PY";
        speed = 4;
        hp = 60;
        attack = 15;

        hitBox.x = 20;
        hitBox.y = 20;
        hitBox.width = 64;
        hitBox.height = 64;

        original_hitbox_x = hitBox.x;
        original_hitbox_y = hitBox.y;

        direction = "right";

        EnemyAssets.Init();
        texture = EnemyAssets.PY;
    }
}
