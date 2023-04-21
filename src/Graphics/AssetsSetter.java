package Graphics;

import GameWindow.Game;
import Objects.Obj_KeyBoard;

public class AssetsSetter {
    Game gp;

    public AssetsSetter(Game gp) {
        this.gp = gp;
    }

    public void setObject() {
        gp.obj[0] = new Obj_KeyBoard();
        gp.obj[0].setCoords(10, 10);
    }
}
