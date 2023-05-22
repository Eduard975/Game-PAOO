package Entity.EmenyClasses;

import Entity.Entity;
import GameWindow.Game;
import Graphics.EnemyAssets;

public class EnemyTS extends Entity {
    public EnemyTS(Game gp) {
        super(gp);
        name = "TS";
        speed = 3;
        hp = 30;
        attack = 15;

        hitBox.x = 20;
        hitBox.y = 20;
        hitBox.width = 64;
        hitBox.height = 64;

        original_hitbox_x = hitBox.x;
        original_hitbox_y = hitBox.y;

        direction = "right";

        EnemyAssets.Init();
        texture = EnemyAssets.TS;
    }
}
