package Entity.EmenyClasses;

import Entity.Entity;
import GameWindow.Game;
import Graphics.EnemyAssets;

public class EnemyCpp extends Entity {
    public EnemyCpp(Game gp) {
        super(gp);
        name = "Cpp";
        speed = 2;
        hp = 20;
        attack = 10;

        hitBox.x = 20;
        hitBox.y = 20;
        hitBox.width = 64;
        hitBox.height = 64;

        original_hitbox_x = hitBox.x;
        original_hitbox_y = hitBox.y;

        direction = "right";

        EnemyAssets.Init();
        texture = EnemyAssets.Cpp;
    }
}
