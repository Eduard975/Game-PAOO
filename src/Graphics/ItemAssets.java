package Graphics;

import java.awt.image.BufferedImage;

public class ItemAssets {
    /// Referinte catre elementele grafice (dale) utilizate in joc.
    /// Elementele acestea sunt temporare
    public static BufferedImage keyboard;

    public static void Init() {
        /// Se creaza temporar un obiect SpriteSheet initializat prin intermediul clasei ImageLoader

        SpriteSheet sheet = new SpriteSheet(ImageLoader.LoadImage("/Resources/Textures/keyboard.png"));

        /// Se obtin subimaginile corespunzatoare elementelor necesare.
        keyboard = sheet.crop(0, 0);
    }
}
