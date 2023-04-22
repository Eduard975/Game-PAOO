package Graphics;

import java.awt.image.BufferedImage;

public class ItemAssets {
    public static BufferedImage keyboard;

    public static void Init() {

        SpriteSheet sheet = new SpriteSheet(ImageLoader.LoadImage("/Resources/Textures/keyboard.png"));

        keyboard = sheet.crop(0, 0);
    }
}
