package Graphics;

import java.awt.image.BufferedImage;

public class EnemyAssets {
    /// Referinte catre inamicii utilizati in joc.
    public static BufferedImage C;
    public static BufferedImage Cpp;
    public static BufferedImage JS;
    public static BufferedImage TS;

    public static BufferedImage PY;
    public static BufferedImage RS;

    public static void Init() {
        /// Se creaza un obiect SpriteSheet initializat prin intermediul clasei ImageLoader

        SpriteSheet sheet = new SpriteSheet(ImageLoader.LoadImage("/Resources/Textures/enemy_sheet.png"));

        /// Se obtin subimaginile corespunzatoare elementelor necesare.
        C = sheet.crop(0, 0, 64, 64);
        Cpp = sheet.crop(2, 1, 64, 64);

        JS = sheet.crop(1, 0, 64, 64);
        TS = sheet.crop(2, 0, 64, 64);

        PY = sheet.crop(0, 1, 64, 64);
        RS = sheet.crop(1, 1, 64, 64);
    }
}
