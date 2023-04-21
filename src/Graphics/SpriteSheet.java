package Graphics;

import java.awt.image.BufferedImage;

public class SpriteSheet {
    private final BufferedImage spriteSheet;    /*!< Referinta catre obiectul BufferedImage ce contine sprite sheet-ul.*/
    private final int tileWidth = 48;          /*!< Latimea unei dale din sprite sheet.*/
    private final int tileHeight = 48;         /*!< Inaltime unei dale din sprite sheet.*/

    public SpriteSheet(BufferedImage buffImg) {
        /// Retine referinta catre BufferedImage object.
        spriteSheet = buffImg;
    }

    public BufferedImage crop(int x, int y, int width, int height) {
        return spriteSheet.getSubimage(x * width, y * height, width, height);
    }

    public BufferedImage crop(int x, int y) {
        return spriteSheet.getSubimage(x * tileWidth, y * tileHeight, tileWidth, tileHeight);
    }
}
