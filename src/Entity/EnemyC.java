package Entity;

import GameWindow.Game;
import Graphics.EnemyAssets;

public class EnemyC extends Entity {
    public EnemyC(Game gp) {
        super(gp);
        name = "C";
        speed = 2;
        hp = 40;

        hitBox.x = 20;
        hitBox.y = 20;
        hitBox.width = 64;
        hitBox.height = 64;

        original_hitbox_x = hitBox.x;
        original_hitbox_y = hitBox.y;

        direction = "right";

        EnemyAssets.Init();
        texture = EnemyAssets.C;
    }
}
