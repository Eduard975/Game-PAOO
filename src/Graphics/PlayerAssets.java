package Graphics;

import java.awt.image.BufferedImage;

/*! \class public class Assets
    \brief Clasa incarca fiecare element grafic necesar jocului.

    Game assets include tot ce este folosit intr-un joc: imagini, sunete, harti etc.
 */
public class PlayerAssets {
    /// Referinte catre elementele grafice (dale) utilizate in joc.
    public static BufferedImage heroLeft;
    public static BufferedImage heroRight;

    /*! \fn public static void Init()
        \brief Functia initializaza referintele catre elementele grafice utilizate.

        Aceasta functie poate fi rescrisa astfel incat elementele grafice incarcate/utilizate
        sa fie parametrizate. Din acest motiv referintele nu sunt finale.
     */
    public static void Init(int option)
    {
        /// Se creaza temporar un obiect SpriteSheet initializat prin intermediul clasei ImageLoader
        SpriteSheet sheet = new SpriteSheet(ImageLoader.LoadImage("/textures/player_sheet.png"));


        /// Se obtin subimaginile corespunzatoare elementelor necesare.
        switch (option) {
            case 0 -> {
                heroRight = sheet.crop(0, 0, 64, 98);
                heroLeft = sheet.crop(2, 1, 64, 98);
            }
            case 1 -> {
                heroRight = sheet.crop(1, 0, 64, 98);
                heroLeft = sheet.crop(1, 1, 64, 98);
            }
            case 2 -> {
                heroRight = sheet.crop(2, 0, 64, 98);
                heroLeft = sheet.crop(0, 1, 64, 98);
            }
        }
    }
}
