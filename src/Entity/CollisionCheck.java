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

        int entityLeft_col = (entityLeft_x - entity.speed) / Game.getDefaultTileSize();
        int entityRight_col = (entityRight_x + entity.speed) / Game.getDefaultTileSize();
        int entityTop_row = (entityTop_y - entity.speed) / Game.getDefaultTileSize();
        int entityBottom_row = (entityBottom_y + entity.speed) / Game.getDefaultTileSize();

        switch (entity.direction) {
            case "up" -> {
                tileNum_up_down(entity, entityLeft_col, entityRight_col, entityTop_row);
            }
            case "down" -> {
                tileNum_up_down(entity, entityLeft_col, entityRight_col, entityBottom_row);
            }
            case "left" -> {
                tileNum_left_right(entity, entityLeft_col, entityTop_row, entityBottom_row);
            }
            case "right" -> {
                tileNum_left_right(entity, entityRight_col, entityTop_row, entityBottom_row);
            }
        }
    }

    private void tileNum_up_down(Entity entity, int entityLeft_col, int entityRight_col, int entity_row) {
        int tileNum1 = gp.tileManager.map[entityLeft_col][entity_row];
        int tileNum2 = gp.tileManager.map[entityRight_col][entity_row];
        if (gp.tileManager.getCollision(tileNum1) || gp.tileManager.getCollision(tileNum2)) {
            entity.is_collided = true;
        }
    }

    private void tileNum_left_right(Entity entity, int entity_col, int entityTop_row, int entityBottom_row) {
        int tileNum1 = gp.tileManager.map[entity_col][entityTop_row];
        int tileNum2 = gp.tileManager.map[entity_col][entityTop_row + 1];
        int tileNum3 = gp.tileManager.map[entity_col][entityBottom_row];
        if (gp.tileManager.getCollision(tileNum1) || gp.tileManager.getCollision(tileNum2) || gp.tileManager.getCollision(tileNum3)) {
            entity.is_collided = true;
        }
    }

    public int checkObject(Entity entity, boolean is_player) {
        int index = 999;

        for (int i = 0; i < gp.obj.length; i++) {
            if (gp.obj[i] != null) {
                //Pozitia hitboxului caracterului
                entity.hitBox.x = entity.world_x + entity.hitBox.x;
                entity.hitBox.y = entity.world_y + entity.hitBox.y;

                //pozitia hitboxului obiectului
                gp.obj[i].hitBox.x = gp.obj[i].x + gp.obj[i].hitBox.x;
                gp.obj[i].hitBox.y = gp.obj[i].y + gp.obj[i].hitBox.y;

                switch (entity.direction) {
                    case "up" -> {
                        entity.hitBox.y += entity.speed;
                        if (entity.hitBox.intersects(gp.obj[i].hitBox)) {
                            if (gp.obj[i].isSolid) {
                                entity.is_collided = true;
                            }
                            if (is_player) {
                                index = i;
                            }
                        }
                    }
                    case "down" -> {
                        entity.hitBox.y -= entity.speed;
                        if (entity.hitBox.intersects(gp.obj[i].hitBox)) {
                            if (gp.obj[i].isSolid) {
                                entity.is_collided = true;
                            }
                            if (is_player) {
                                index = i;
                            }
                        }
                    }
                    case "left" -> {
                        entity.hitBox.x -= entity.speed;
                        if (entity.hitBox.intersects(gp.obj[i].hitBox)) {
                            if (gp.obj[i].isSolid) {
                                entity.is_collided = true;
                            }
                            if (is_player) {
                                index = i;
                            }
                        }
                    }
                    case "right" -> {
                        entity.hitBox.x += entity.speed;
                        if (entity.hitBox.intersects(gp.obj[i].hitBox)) {
                            if (gp.obj[i].isSolid) {
                                entity.is_collided = true;
                            }
                            if (is_player) {
                                index = i;
                            }
                        }
                    }
                }

                entity.hitBox.x = entity.original_hitbox_x;
                entity.hitBox.y = entity.original_hitbox_y;

                gp.obj[i].hitBox.x = gp.obj[i].original_hitbox_x;
                gp.obj[i].hitBox.y = gp.obj[i].original_hitbox_y;
            }
        }
        return index;
    }

}
