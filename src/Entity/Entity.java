package Entity;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {
    public int x, y;
    public int world_x, world_y;
    public int speed;
    public Rectangle hitBox;
    public boolean is_collided = false;
    public BufferedImage texture;
    public String direction;
}
