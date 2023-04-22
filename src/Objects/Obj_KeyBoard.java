package Objects;

import Graphics.ItemAssets;

public class Obj_KeyBoard extends SuperObject {
    public Obj_KeyBoard() {
        name = "keyBoard";
        hitBox.x = 5;
        hitBox.y = 18;

        original_hitbox_x = hitBox.x;
        original_hitbox_y = hitBox.y;

        ItemAssets.Init();
        image = ItemAssets.keyboard;
    }
}
