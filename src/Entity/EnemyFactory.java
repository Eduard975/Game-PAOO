package Entity;

import Entity.EmenyClasses.*;
import GameWindow.Game;

import java.util.Random;

public class EnemyFactory {
    public boolean coinFlip() {
        Random random = new Random();
        return random.nextBoolean();
    }

    public Entity makeEnemy(int map_id, Game gp) {
        Entity enemy = null;

        switch (map_id) {
            case 0 -> {
                if (coinFlip())
                    enemy = new EnemyC(gp);
                else
                    enemy = new EnemyCpp(gp);
            }
            case 1 -> {
                if (coinFlip())
                    enemy = new EnemyJS(gp);
                else
                    enemy = new EnemyTS(gp);
            }
            case 2 -> {
                if (coinFlip())
                    enemy = new EnemyRS(gp);
                else
                    enemy = new EnemyPY(gp);
            }
        }

        return enemy;
    }
}
