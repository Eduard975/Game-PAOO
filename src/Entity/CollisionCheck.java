package Entity;

import GameWindow.Game;

public class CollisionCheck {
    Game gp;

    public CollisionCheck(Game gp) {
        this.gp = gp;
    }

    public void checkTile(Entity entity) {
        int entityLeft_x = entity.world_x + entity.hitBox.x;
        int entityRight_x = entity.world_x + entity.hitBox.x + entity.hitBox.width;
        int entityTop_y = entity.world_y + entity.hitBox.y;
        int entityBottom_y = entity.world_y + entity.hitBox.y + entity.hitBox.height;

        int entityLeft_col = (entityLeft_x - entity.speed) / gp.getDefaultTileSize();
        int entityRight_col = (entityRight_x + entity.speed) / gp.getDefaultTileSize();
        int entityTop_row = (entityTop_y - entity.speed) / gp.getDefaultTileSize();
        int entityBottom_row = (entityBottom_y + entity.speed) / gp.getDefaultTileSize();

        int tileNum1, tileNum2;

        switch (entity.direction) {
            case "up" -> {
                tileNum1 = gp.tileManager.map[entityLeft_col][entityTop_row];
                tileNum2 = gp.tileManager.map[entityRight_col][entityTop_row];
                if (gp.tileManager.getCollision(tileNum1) || gp.tileManager.getCollision(tileNum2)) {
                    entity.is_collided = true;
                }
            }
            case "down" -> {
                tileNum1 = gp.tileManager.map[entityLeft_col][entityBottom_row];
                tileNum2 = gp.tileManager.map[entityRight_col][entityBottom_row];
                if (gp.tileManager.getCollision(tileNum1) || gp.tileManager.getCollision(tileNum2)) {
                    entity.is_collided = true;
                }
            }
            case "left" -> {
                tileNum1 = gp.tileManager.map[entityLeft_col][entityTop_row];
                tileNum2 = gp.tileManager.map[entityLeft_col][entityTop_row + 1];
                int tileNum3 = gp.tileManager.map[entityLeft_col][entityBottom_row];
                if (gp.tileManager.getCollision(tileNum1) || gp.tileManager.getCollision(tileNum2) || gp.tileManager.getCollision(tileNum3)) {
                    entity.is_collided = true;
                }
            }
            case "right" -> {
                tileNum1 = gp.tileManager.map[entityRight_col][entityTop_row];
                tileNum2 = gp.tileManager.map[entityRight_col][entityTop_row + 1];
                int tileNum3 = gp.tileManager.map[entityRight_col][entityBottom_row];
                if (gp.tileManager.getCollision(tileNum1) || gp.tileManager.getCollision(tileNum2) || gp.tileManager.getCollision(tileNum3)) {
                    entity.is_collided = true;
                }
            }
        }
    }

}
