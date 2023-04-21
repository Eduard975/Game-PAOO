package GameWindow;

import Entity.CollisionCheck;
import Entity.Player;
import Graphics.AssetsSetter;
import Input.KeyManager;
import Map.TileManager;
import Objects.SuperObject;

import javax.swing.*;
import java.awt.*;

public class Game extends JPanel implements Runnable {
    static final int default_TileSize = 48;
    public int tileSize = default_TileSize;

    //Camera parameters
    final int maxScreenCol = 25;
    final int maxScreenRow = 15;
    final int screenWidth = default_TileSize * maxScreenCol; //1200
    final int screenHeight = default_TileSize * maxScreenRow; //920


    //World parameters
    int maxWorldCol = 75;
    int maxWorldRow = 45;
    final int worldWidth = default_TileSize * maxWorldCol;
    final int worldHeight = default_TileSize * maxWorldRow;
    int FPS = 60;

    KeyManager KeyMan = new KeyManager();
    Thread gameThread;
    public Player player = Player.getInstance(this, KeyMan);

    public AssetsSetter assets_setter = new AssetsSetter(this);
    public SuperObject[] obj = new SuperObject[6];
    public CollisionCheck colCheck = new CollisionCheck(this);
    public TileManager tileManager = new TileManager(this);

    public Game() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(KeyMan);
        this.setFocusable(true);
    }

    public void setupGame() {
        assets_setter.setObject();
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double drawinterval = (double) 1000000000 / FPS;
        double nextDrawTime = System.nanoTime() + drawinterval;
        while (gameThread != null) {
            update();
            repaint();
            try {
                double remaningTime = nextDrawTime - System.nanoTime();
                remaningTime = remaningTime / 1000000;

                if (remaningTime < 0)
                    remaningTime = 0;

                Thread.sleep((long) remaningTime);

                nextDrawTime += drawinterval;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }
    }

    public void update() {
        player.update();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2D = (Graphics2D) g;

        tileManager.draw(g2D);

        obj[0].draw(g2D, this);

        player.draw(g2D);

        g2D.dispose();
    }

    public int getMaxWorldRow() {
        return maxWorldRow;
    }

    public int getMaxWorldCol() {
        return maxWorldCol;
    }

    public int getScreenWidth() {
        return screenWidth;
    }

    public int getScreenHeight() {
        return screenHeight;
    }

    public static int getDefaultTileSize() {
        return default_TileSize;
    }
}
