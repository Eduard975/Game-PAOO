package Entity;

import GameWindow.Game;
import Graphics.ItemAssets;

public class projectile_VIM extends Projectile {
    double CD = 2;

    public projectile_VIM(Game gp) {
        super(gp);
        speed = 8;

        hitBox.width = 48;
        hitBox.height = 48;

        ItemAssets.Init();
        texture = ItemAssets.vim;

        direction = "right";
    }

}
