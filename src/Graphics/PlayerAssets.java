package Graphics;

import java.awt.image.BufferedImage;


public class PlayerAssets {
    public static BufferedImage heroLeft;
    public static BufferedImage heroRight;

    public static void Init(int option) {
        SpriteSheet sheet = new SpriteSheet(ImageLoader.LoadImage("/Resources/Textures/player_sheet.png"));

        /// Se obtin subimaginile corespunzatoare elementelor necesare.
        switch (option) {
            case 0 -> {
                heroRight = sheet.crop(0, 0, 64, 98);
                heroLeft = sheet.crop(2, 1, 64, 98);
            }
            case 1 -> {
                heroRight = sheet.crop(1, 0, 64, 98);
                heroLeft = sheet.crop(1, 1, 64, 98);
            }
            case 2 -> {
                heroRight = sheet.crop(2, 0, 64, 98);
                heroLeft = sheet.crop(0, 1, 64, 98);
            }
        }
    }
}
