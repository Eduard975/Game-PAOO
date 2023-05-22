package Entity.EmenyClasses;

import Entity.Entity;
import GameWindow.Game;
import Graphics.EnemyAssets;

public class EnemyRS extends Entity {
    public EnemyRS(Game gp) {
        super(gp);
        name = "RS";
        speed = 4;
        hp = 100;
        attack = 20;

        hitBox.x = 20;
        hitBox.y = 20;
        hitBox.width = 64;
        hitBox.height = 64;

        original_hitbox_x = hitBox.x;
        original_hitbox_y = hitBox.y;

        direction = "right";

        EnemyAssets.Init();
        texture = EnemyAssets.RS;
    }
}
