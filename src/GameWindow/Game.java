package GameWindow;

import Input.KeyManager;

import javax.swing.*;
import java.awt.*;

/*! \class GameWindow
    \brief Implementeaza notiunea de fereastra a jocului.

    Membrul wndFrame este un obiect de tip JFrame care va avea utilitatea unei
    ferestre grafice si totodata si cea a unui container (toate elementele
    grafice vor fi continute de fereastra).
 */
public class Game extends JPanel implements Runnable
{
    final int default_TileSize = 64;
    int tileSize = default_TileSize;

    final int maxScreenCol = 16;
    final int getMaxScreenRow = 12;

    final int screenWidth = default_TileSize * maxScreenCol; //1024
    final int screenHeight = default_TileSize * getMaxScreenRow; //768

    int FPS = 60;

    int player_x = 100;
    int player_y = 100;
    int player_speed = 4;
    Thread gameThread;
    KeyManager KeyMan = new KeyManager();
    public Game() {
        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(KeyMan);
        this.setFocusable(true);
    }
    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }
    @Override
    public void run() {
        double drawinterval = (double) 1000000000 /FPS;
        double nextDrawTime = System.nanoTime() + drawinterval;
        while (gameThread != null){
            System.out.println("tagadam");
            update();
            repaint();

            try {
                double remaningTime = nextDrawTime - System.nanoTime();
                remaningTime = remaningTime/1000000;

                if(remaningTime < 0)
                    remaningTime = 0;

                Thread.sleep((long) remaningTime);

                nextDrawTime += drawinterval;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }
    }

    public void update(){
        if (KeyMan.W) {
            player_y -= player_speed;
        }

        if (KeyMan.S) {
            player_y += player_speed;
        }

        if (KeyMan.A) {
            player_x -= player_speed;
        }

        if (KeyMan.D) {
            player_x += player_speed;
        }
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D g2D= (Graphics2D) g;

        g2D.setColor(Color.white);

        g2D.fillRect(player_x, player_y,64,64);

        g2D.dispose();
    }
}
