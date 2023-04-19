package Graphics;

import java.awt.image.BufferedImage;

/*! \class public class Assets
    \brief Clasa incarca fiecare element grafic necesar jocului.

    Game assets include tot ce este folosit intr-un joc: imagini, sunete, harti etc.
 */
public class Assets
{
        /// Referinte catre elementele grafice (dale) utilizate in joc.
    public static BufferedImage heroLeft;
    public static BufferedImage heroRight;
    public static BufferedImage soil;
    public static BufferedImage grass;
    public static BufferedImage mountain;
    public static BufferedImage townGrass;
    public static BufferedImage townGrassDestroyed;
    public static BufferedImage townSoil;
    public static BufferedImage water;
    public static BufferedImage rockUp;
    public static BufferedImage rockDown;
    public static BufferedImage rockLeft;
    public static BufferedImage rockRight;
    public static BufferedImage tree;

    /*! \fn public static void Init()
        \brief Functia initializaza referintele catre elementele grafice utilizate.

        Aceasta functie poate fi rescrisa astfel incat elementele grafice incarcate/utilizate
        sa fie parametrizate. Din acest motiv referintele nu sunt finale.
     */
    public static void Init()
    {
            /// Se creaza temporar un obiect SpriteSheet initializat prin intermediul clasei ImageLoader
        SpriteSheet sheet = new SpriteSheet(ImageLoader.LoadImage("/textures/enemy_sheet.png"));
        SpriteSheet sheet4 = new SpriteSheet(ImageLoader.LoadImage("/textures/floor_sheet.png"));
        SpriteSheet sheet3 = new SpriteSheet(ImageLoader.LoadImage("/textures/char_sheet.png"));
        SpriteSheet sheet2 = new SpriteSheet(ImageLoader.LoadImage("/textures/PaooGameSpriteSheet.png"));

            /// Se obtin subimaginile corespunzatoare elementelor necesare.
        grass = sheet4.crop(0, 0, 32, 32);
        soil = sheet.crop(1, 0,64, 64);
        water = sheet.crop(2, 0,64, 64);
        mountain = sheet.crop(1, 1,64, 64);

        townGrass = sheet2.crop(0, 1, 48, 48);
        townGrassDestroyed = sheet2.crop(1, 1, 48, 48);
        townSoil = sheet2.crop(2, 1, 48, 48);
        tree = sheet2.crop(3, 1, 48, 48);

        rockUp = sheet2.crop(2, 2, 48, 48);
        rockDown = sheet2.crop(3, 2, 48, 48);
        rockLeft = sheet2.crop(0, 3, 48, 48);
        rockRight = sheet2.crop(1, 3, 48, 48);

        heroLeft = sheet3.crop(0, 0, 64, 98);
        heroRight = sheet3.crop(1, 0, 64, 98);
    }
}
