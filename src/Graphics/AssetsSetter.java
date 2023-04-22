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
        gp.obj[0].setCoords(gp.getMaxWorldCol() / 2, (gp.getMaxWorldRow() / 2) - 5);

        gp.obj[1] = new Obj_KeyBoard();
        gp.obj[1].setCoords(75, 35);

        gp.obj[2] = new Obj_KeyBoard();
        gp.obj[2].setCoords(50, 15);
    }
}
