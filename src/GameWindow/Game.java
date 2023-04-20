package GameWindow;

import Entity.Player;
import Input.KeyManager;
import Map.TileManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentListener;

/*! \class GameWindow
    \brief Implementeaza notiunea de fereastra a jocului.

    Membrul wndFrame este un obiect de tip JFrame care va avea utilitatea unei
    ferestre grafice si totodata si cea a unui container (toate elementele
    grafice vor fi continute de fereastra).
 */
public class Game extends JPanel implements Runnable
{
    final int default_TileSize = 48;
    public int tileSize = default_TileSize;

    final int maxScreenCol = 25;
    final int maxScreenRow = 15;

    int maxWorldCol = 2500;
    int maxWorldRow = 1500;
    final int screenWidth = default_TileSize * maxScreenCol; //1200
    final int screenHeight = default_TileSize * maxScreenRow; //960

    int FPS = 60;

    KeyManager KeyMan = new KeyManager();
    Thread gameThread;
    public Player player = new Player(this, KeyMan);

    TileManager tileManager = new TileManager(this);
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
        player.update();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D g2D = (Graphics2D) g;

        tileManager.draw(g2D);
        player.draw(g2D);

        g2D.dispose();
    }

    public int getMaxScreenRow(){
        return maxScreenRow;
    }

    public int getMaxScreenCol(){
        return maxScreenCol;
    }

    public int getMaxWorldRow(){
        return maxWorldRow;
    }

    public int getMaxWorldCol(){
        return maxWorldCol;
    }

    public int getScreenWidth(){
        return screenWidth;
    }

    public int getScreenHeight(){
        return screenHeight;
    }

    public int getDefaultTileSize(){
        return default_TileSize;
    }
}
