package Graphics;

import java.awt.image.BufferedImage;


public class PlayerAssets {
    public static BufferedImage heroLeft;
    public static BufferedImage heroRight;

    public static int player_w = 64;
    public static int player_h = 98;

    public static SpriteSheet sheet = new SpriteSheet(ImageLoader.LoadImage("/Resources/Textures/player_sheet.png"));

    public static void Init(int option) {

        /// Se obtin subimaginile corespunzatoare elementelor necesare.
        switch (option) {
            case 0 -> {
                heroRight = sheet.crop(0, 0, player_w, player_h);
                heroLeft = sheet.crop(2, 1, player_w, player_h);
            }
            case 1 -> {
                heroRight = sheet.crop(1, 0, player_w, player_h);
                heroLeft = sheet.crop(1, 1, player_w, player_h);
            }
            case 2 -> {
                heroRight = sheet.crop(2, 0, player_w, player_h);
                heroLeft = sheet.crop(0, 1, player_w, player_h);
            }
        }
    }

    public static BufferedImage getHeroRight(int option) {
        switch (option) {
            case 0 -> {
                return sheet.crop(0, 0, player_w, player_h);
            }
            case 1 -> {
                return sheet.crop(1, 0, player_w, player_h);
            }
            case 2 -> {
                return sheet.crop(2, 0, player_w, player_h);

            }
        }
        return null;
    }
}