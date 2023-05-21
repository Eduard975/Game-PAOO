package Graphics;

import GameWindow.Game;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

public class UI {
    Game gp;
    Font font_40;

    DecimalFormat dFormat = new DecimalFormat(":00");
    double playTime_sec;
    int playTime_min;

    BufferedImage[] maps;

    int subMenuState = 0; // 0 - first screen, 1 - char selection, 2 - map selection, 3-run done
    int selectedCommand = 2;

    public UI(Game gp) {
        this.gp = gp;
        this.font_40 = new Font("SansSerif", Font.PLAIN, 40);
        this.maps = new BufferedImage[3];
        maps[0] = ImageLoader.LoadImage("/Resources/Maps/map1_preview.png");
        maps[1] = ImageLoader.LoadImage("/Resources/Maps/map2_preview.png");
        maps[2] = ImageLoader.LoadImage("/Resources/Maps/map3_preview.png");
    }

    public void draw(Graphics2D g2D) {
        if (gp.getMenuState() == 1) {
            if (subMenuState == 0) {
                drawMainMenuScreen(g2D);
            } else if (subMenuState == 1) {
                drawSubMenu1(g2D);
            } else if (subMenuState == 2) {
                drawSubMenu2(g2D);
            } else if (subMenuState == 3) {
                drawEndScreen(g2D);
            }
        } else {
            g2D.setFont(font_40);
            g2D.setColor(Color.PINK);
            g2D.drawString("Speed = " + gp.player.speed, 50, 50);
            if (gp.getPlayState()) {
                playTime_sec += (double) 1 / 60;
                if (playTime_sec >= (double) 59) {
                    playTime_min++;
                    playTime_sec = (double) 0;
                }
            } else {
                g2D.setFont(font_40.deriveFont(Font.PLAIN, 80));
                g2D.drawString("PAUSED", getCenterX(g2D, "PAUSED"), gp.getScreenHeight() / 2);
                g2D.setFont(font_40);
            }
            g2D.drawString("" + playTime_min + dFormat.format(playTime_sec), gp.getScreenWidth() / 2 - 10, 50);
            if (playTime_min == 1) {
                gp.setMenuState(1);
                gp.setPlayState(false);
                subMenuState = 3;
            }
        }
    }

    public void drawMainMenuScreen(Graphics2D g2D) {
        g2D.setColor(Color.DARK_GRAY);
        g2D.fillRect(0, 0, gp.getScreenWidth(), gp.getScreenHeight());
        g2D.setFont(font_40.deriveFont(Font.PLAIN, 96F));
        String text = "Mesterul Grigore";
        int x = getCenterX(g2D, text);
        int y = gp.getDefaultTileSize() * 3;
        g2D.setColor(Color.RED);
        g2D.drawString(text, x, y);

        g2D.setColor(Color.WHITE);
        g2D.setFont(font_40.deriveFont(Font.PLAIN, 60F));
        text = "New Game";
        x = getCenterX(g2D, text);
        y += gp.getDefaultTileSize() * 4;
        g2D.drawString(text, x, y);
        if (selectedCommand == 2) {
            g2D.drawString("→", (int) (x - gp.getDefaultTileSize() * 1.5), y);
        }

        text = "High Score";
        x = getCenterX(g2D, text);
        y += gp.getDefaultTileSize() * 2;
        g2D.drawString(text, x, y);
        if (selectedCommand == 1) {
            g2D.drawString("→", (int) (x - gp.getDefaultTileSize() * 1.5), y);
        }

        text = "Quit";
        x = getCenterX(g2D, text);
        y += gp.getDefaultTileSize() * 3;
        g2D.drawString(text, x, y);
        if (selectedCommand == 0) {
            g2D.drawString("→", (int) (x - gp.getDefaultTileSize() * 1.5), y);
        }
    }

    public void drawSubMenu1(Graphics2D g2D) {
        g2D.setColor(Color.DARK_GRAY);
        g2D.fillRect(0, 0, gp.getScreenWidth(), gp.getScreenHeight());
        String text;
        int x, y;

        g2D.setFont(font_40.deriveFont(Font.PLAIN, 60F));
        text = "Select a Character";
        x = getCenterX(g2D, text);
        y = gp.getDefaultTileSize() * 3;
        g2D.setColor(Color.RED);
        g2D.drawString(text, x, y);

        displayCharacters(g2D);
    }

    public void drawSubMenu2(Graphics2D g2D) {
        g2D.setColor(Color.DARK_GRAY);
        g2D.fillRect(0, 0, gp.getScreenWidth(), gp.getScreenHeight());
        String text;
        int x, y;

        g2D.setFont(font_40.deriveFont(Font.PLAIN, 60F));
        text = "Select a Map";
        x = getCenterX(g2D, text);
        y = gp.getDefaultTileSize() * 3;
        g2D.setColor(Color.RED);
        g2D.drawString(text, x, y);

        displayMaps(g2D);
    }

    public void drawEndScreen(Graphics2D g2D) {
        g2D.setColor(Color.DARK_GRAY);
        g2D.fillRect(0, 0, gp.getScreenWidth(), gp.getScreenHeight());
        g2D.setFont(font_40.deriveFont(Font.PLAIN, 96F));

        String text = "Felicitari! Ai supravietuit";
        int x = getCenterX(g2D, text);
        int y = gp.getDefaultTileSize() * 3;
        g2D.setColor(Color.RED);
        g2D.drawString(text, x, y);

        g2D.setColor(Color.pink);
        g2D.setFont(font_40.deriveFont(Font.PLAIN, 60F));
        text = "Scor " + gp.player.speed;
        x = getCenterX(g2D, text);
        y += gp.getDefaultTileSize() * 4;
        g2D.drawString(text, x, y);

        g2D.setFont(font_40.deriveFont(Font.PLAIN, 40F));
        text = "Return to Menu";
        x = getCenterX(g2D, text);
        y += gp.getDefaultTileSize() * 4;
        g2D.drawString(text, x, y);
        g2D.drawString("→", (int) (x - gp.getDefaultTileSize() * 1.5), y);

    }

    public void displayCharacters(Graphics2D g2D) {
        int size_w = PlayerAssets.player_w * 4;
        int size_h = PlayerAssets.player_h * 4;

        int x = gp.getScreenWidth() / 2 - size_w / 2;
        int y = gp.getScreenHeight() / 2 - size_h / 4;

        g2D.setFont(font_40.deriveFont(Font.PLAIN, 80F));
        g2D.setColor(Color.PINK);

        g2D.drawImage(PlayerAssets.getHeroRight(0), x - size_w, y, size_w, size_h, null);
        if (selectedCommand == 2) {
            g2D.drawString("↓", x - size_w / 2 - 5, y + gp.getDefaultTileSize() / 4);
        }
        g2D.drawImage(PlayerAssets.getHeroRight(1), x, y, size_w, size_h, null);
        if (selectedCommand == 1) {
            g2D.drawString("↓", x + size_w / 2 - 5, y + gp.getDefaultTileSize() / 4);
        }
        g2D.drawImage(PlayerAssets.getHeroRight(2), x + size_w, y, size_w, size_h, null);
        if (selectedCommand == 0) {
            g2D.drawString("↓", x + size_w / 2 + size_w - 5, y + gp.getDefaultTileSize() / 4);
        }
    }

    public void displayMaps(Graphics2D g2D) {
        int img_w = 1502 / 2;
        int img_h = 896 / 10;

        int x = gp.getScreenWidth() / 2 - img_w / 2;
        int y = gp.getDefaultTileSize() * 5;

        g2D.setFont(font_40.deriveFont(Font.PLAIN, 80F));
        g2D.setColor(Color.PINK);

        g2D.drawImage(maps[0], x, y, img_w, img_h, null);
        if (selectedCommand == 2) {
            g2D.drawString("→", x - 100, y + img_h / 2 + 10);
        }

        g2D.drawImage(maps[1], x, y + gp.getDefaultTileSize() * 3, img_w, img_h, null);
        if (selectedCommand == 1) {
            g2D.drawString("→", x - 100, y + gp.getDefaultTileSize() * 3 + img_h / 2 + 10);
        }

        g2D.drawImage(maps[2], x, y + gp.getDefaultTileSize() * 6, img_w, img_h, null);
        if (selectedCommand == 0) {
            g2D.drawString("→", x - 100, y + gp.getDefaultTileSize() * 6 + img_h / 2 + 10);
        }

    }


    public int getCenterX(Graphics2D g2D, String text) {
        int length = (int) g2D.getFontMetrics().getStringBounds(text, g2D).getWidth();
        return gp.getScreenWidth() / 2 - length / 2;
    }

    public void setSelectedCommand(int x) {
        selectedCommand = x;
    }

    public int getSelectedCommand() {
        return selectedCommand;
    }

    public int getSubMenuState() {
        return subMenuState;
    }

    public void setSubMenuState(int x) {
        subMenuState = x;
    }
}
