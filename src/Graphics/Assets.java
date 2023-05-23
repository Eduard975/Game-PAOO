package Graphics;

import java.awt.image.BufferedImage;

public class Assets {
    /// Referinte catre elementele grafice (dale) utilizate in joc.
    public static BufferedImage soil, grass, water, void1, outer_wall, castle_floor, castle_middle, sand;

    public static void Init() {
        /// Se creaza un obiect SpriteSheet initializat prin intermediul clasei ImageLoader

        SpriteSheet sheet = new SpriteSheet(ImageLoader.LoadImage("/Resources/Textures/sprite_sheet.png"));
        //SpriteSheet img = new SpriteSheet(ImageLoader.LoadImage("/Resources/Textures/floor3.png"));
        //SpriteSheet img1 = new SpriteSheet(ImageLoader.LoadImage("/Resources/Textures/mid_castle_floor.png"));
        //SpriteSheet img2 = new SpriteSheet(ImageLoader.LoadImage("/Resources/Textures/outer_wall.png"));
        //SpriteSheet img3 = new SpriteSheet(ImageLoader.LoadImage("/Resources/Textures/sand.png"));
        /// Se obtin subimaginile corespunzatoare elementelor necesare.
        grass = sheet.crop(0, 0);
        soil = sheet.crop(1, 0);
        sand = sheet.crop(2, 0);

        outer_wall = sheet.crop(3, 0);
        castle_middle = sheet.crop(4, 0);
        castle_floor = sheet.crop(5, 0);

        void1 = sheet.crop(0, 1);
        water = sheet.crop(1, 1);
    }
}
