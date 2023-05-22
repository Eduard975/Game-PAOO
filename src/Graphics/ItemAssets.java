package Graphics;

import java.awt.image.BufferedImage;

public class ItemAssets {
    public static BufferedImage keyboard, vim;

    public static void Init() {

        SpriteSheet sheet = new SpriteSheet(ImageLoader.LoadImage("/Resources/Textures/keyboard.png"));
        SpriteSheet sheet2 = new SpriteSheet(ImageLoader.LoadImage("/Resources/Textures/projectile.png"));

        keyboard = sheet.crop(0, 0);

        vim = sheet.crop(0, 0, 32, 32);

    }
}
