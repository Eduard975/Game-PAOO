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

        int entityLeft_col = (entityLeft_x - entity.speed) / Game.getDefaultTileSize_s();
        int entityRight_col = (entityRight_x + entity.speed) / Game.getDefaultTileSize_s();
        int entityTop_row = (entityTop_y - entity.speed) / Game.getDefaultTileSize_s();
        int entityBottom_row = (entityBottom_y + entity.speed) / Game.getDefaultTileSize_s();

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
        int tileNum1 = gp.tileManager.map[entity_row][entityLeft_col];
        int tileNum2 = gp.tileManager.map[entity_row][entityRight_col];
        if (gp.tileManager.getCollision(tileNum1) || gp.tileManager.getCollision(tileNum2)) {
            entity.is_collided = true;
        }
    }

    private void tileNum_left_right(Entity entity, int entity_col, int entityTop_row, int entityBottom_row) {
        int tileNum1 = gp.tileManager.map[entityTop_row + 1][entity_col];
        int tileNum2 = gp.tileManager.map[entityTop_row][entity_col];
        int tileNum3 = gp.tileManager.map[entityBottom_row][entity_col];
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

    public int checkEntity(Entity entity, boolean is_player) {
        int index = 999;

        for (int i = 0; i < gp.enemies.length; i++) {
            if (gp.enemies[i] != null) {
                //Pozitia hitboxului caracterului
                entity.hitBox.x = entity.world_x + entity.hitBox.x;
                entity.hitBox.y = entity.world_y + entity.hitBox.y;

                //pozitia hitboxului obiectului
                gp.enemies[i].hitBox.x = gp.enemies[i].x + gp.enemies[i].hitBox.x;
                gp.enemies[i].hitBox.y = gp.enemies[i].y + gp.enemies[i].hitBox.y;

                switch (entity.direction) {
                    case "up" -> {
                        entity.hitBox.y += entity.speed;
                    }
                    case "down" -> {
                        entity.hitBox.y -= entity.speed;
                    }
                    case "left" -> {
                        entity.hitBox.x -= entity.speed;
                    }
                    case "right" -> {
                        entity.hitBox.x += entity.speed;
                    }
                }
                if (entity.hitBox.intersects(gp.enemies[i].hitBox)) {
                    if (is_player) {
                        index = i;
                    }
                }

                entity.hitBox.x = entity.original_hitbox_x;
                entity.hitBox.y = entity.original_hitbox_y;

                gp.enemies[i].hitBox.x = gp.enemies[i].original_hitbox_x;
                gp.enemies[i].hitBox.y = gp.enemies[i].original_hitbox_y;
            }
        }
        return index;
    }
}
