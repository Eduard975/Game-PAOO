package Graphics;

import java.awt.image.BufferedImage;

public class Assets {
    /// Referinte catre elementele grafice (dale) utilizate in joc.
    public static BufferedImage soil;
    public static BufferedImage grass;
    public static BufferedImage water;
    public static BufferedImage void1;

    public static void Init() {
        /// Se creaza un obiect SpriteSheet initializat prin intermediul clasei ImageLoader

        SpriteSheet sheet = new SpriteSheet(ImageLoader.LoadImage("/Resources/Textures/sprite_sheet.png"));

        /// Se obtin subimaginile corespunzatoare elementelor necesare.
        grass = sheet.crop(0, 0);
        soil = sheet.crop(1, 0);

        void1 = sheet.crop(0, 1);
        water = sheet.crop(1, 1);
    }
}
