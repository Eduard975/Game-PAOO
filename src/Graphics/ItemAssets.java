package Graphics;

import java.awt.image.BufferedImage;

public class ItemAssets {
    public static BufferedImage keyboard, vim, skull, heart, gun, shoe;

    public static void Init() {
        SpriteSheet img = new SpriteSheet(ImageLoader.LoadImage("/Resources/Textures/keyboard.png"));
        SpriteSheet img2 = new SpriteSheet(ImageLoader.LoadImage("/Resources/Textures/projectile.png"));
        SpriteSheet sheet = new SpriteSheet(ImageLoader.LoadImage("/Resources/Textures/icon_sheet.png"));

        keyboard = img.crop(0, 0);

        vim = img2.crop(0, 0, 32, 32);

        heart = sheet.crop(0, 0, 16, 16);
        gun = sheet.crop(1, 0, 16, 16);
        shoe = sheet.crop(2, 0, 16, 16);
        skull = sheet.crop(3, 0, 16, 16);
    }
}
