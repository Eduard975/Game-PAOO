package GameWindow;

import javax.swing.*;
import java.awt.*;

/*! \class GameWindow
    \brief Implementeaza notiunea de fereastra a jocului.

    Membrul wndFrame este un obiect de tip JFrame care va avea utilitatea unei
    ferestre grafice si totodata si cea a unui container (toate elementele
    grafice vor fi continute de fereastra).
 */
public class GameWindow extends JPanel
{
    final int default_TileSize = 64;
    int tileSize = default_TileSize;

    final int maxScreenCol = 16;
    final int getMaxScreenRow = 12;

    final int screenWidth = default_TileSize * maxScreenCol; //1024
    final int screenHeight = default_TileSize * getMaxScreenRow; //768

    public void gameWindow() {
        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        
    }

}
