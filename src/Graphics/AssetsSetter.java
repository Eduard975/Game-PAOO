package Graphics;

import Entity.EnemyFactory;
import GameWindow.Game;
import Objects.Obj_KeyBoard;

import java.util.Random;

public class AssetsSetter {
    Game gp;

    EnemyFactory ef = new EnemyFactory();

    public AssetsSetter(Game gp) {
        this.gp = gp;
    }

    private static int next() {
        Random random = new Random();
        if (random.nextBoolean()) {
            return 1;
        } else {
            return -1;
        }
    }

    public void setObject() {
        gp.obj[0] = new Obj_KeyBoard();
        gp.obj[0].setCoords(gp.getMaxWorldCol() / 2, (gp.getMaxWorldRow() / 2) - 5);

        gp.obj[1] = new Obj_KeyBoard();
        gp.obj[1].setCoords(75, 35);

        gp.obj[2] = new Obj_KeyBoard();
        gp.obj[2].setCoords(50, 15);
    }

    public void setEnemy() {
        System.out.println("Incepe nebunia");
        int map_id = gp.tileManager.getMap_option();
        for (int i = 0; i < gp.enemies.length; i++) {
            if (gp.enemies[i] == null) {
                gp.enemies[i] = ef.makeEnemy(map_id, gp);
            }
            gp.enemies[i].y = (gp.player.world_y + 48 * (i * 10 + 10) * next());
            gp.enemies[i].x = (gp.player.world_x + 48 * (i * 10 + 10) * next());
        }

        System.out.println("Se termina nebunia");

    }

    public void newEnemy(int i) {
        int map_id = gp.tileManager.getMap_option();
        if (gp.enemies[i] == null) {
            gp.enemies[i] = ef.makeEnemy(map_id, gp);
        }
        gp.enemies[i].y = (gp.player.world_y + 48 * (i * 10 + 10) * next());
        gp.enemies[i].x = (gp.player.world_x + 48 * (i * 10 + 10) * next());
    }


    public static int getNext() {
        return next();
    }

}
