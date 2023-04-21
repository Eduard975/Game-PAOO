package Graphics;

import java.awt.image.BufferedImage;

public class Assets {
    /// Referinte catre elementele grafice (dale) utilizate in joc.
    /// Elementele acestea sunt temporare
    public static BufferedImage soil;
    public static BufferedImage grass;
    public static BufferedImage water;

    public static void Init() {
        /// Se creaza temporar un obiect SpriteSheet initializat prin intermediul clasei ImageLoader

        SpriteSheet sheet = new SpriteSheet(ImageLoader.LoadImage("/Resources/Textures/PaooGameSpriteSheet.png"));

        /// Se obtin subimaginile corespunzatoare elementelor necesare.
        grass = sheet.crop(0, 0);
        soil = sheet.crop(1, 0);
        water = sheet.crop(2, 0);
    }
}
