package Entity;

import GameWindow.Game;
import Input.KeyManager;
import Graphics.PlayerAssets;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Player extends Entity{
    Game gp;
    KeyManager keyM;

    public int texture_option;

    public Player(Game gp, KeyManager keyM){
        this.gp = gp;
        this.keyM = keyM;

        setDefaultValues();
    }

    public void setDefaultValues(){
        x = gp.getScreenWidth() / 2 - 32;
        y = gp.getScreenHeight() / 2 - 49;

        world_x = gp.getDefaultTileSize() * gp.getMaxWorldCol()/2;
        world_y = gp.getDefaultTileSize() * gp.getMaxWorldRow()/2;

        speed = 4;
        direction = "right";
        texture_option = 1;
    }

    public void update(){
        if (keyM.W) {
            direction = "up";
            world_y -= speed;
        }

        if (keyM.S) {
            direction = "down";
            world_y += speed;
        }

        if (keyM.A) {
            direction = "left";
            world_x -= speed;
        }

        if (keyM.D) {
            direction = "right";
            world_x += speed;
        }


    }

    public void draw(Graphics2D g2D){
        PlayerAssets.Init(texture_option);

        switch (direction) {
            case "left" -> texture = PlayerAssets.heroLeft;
            case "right" -> texture = PlayerAssets.heroRight;
        }

        g2D.drawImage(texture, x, y, 64, 98, null);
    }
}