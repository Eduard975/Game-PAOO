package Graphics;

import GameWindow.Game;

import java.awt.*;
import java.text.DecimalFormat;

public class UI {
    Game gp;
    Font Comic_Sans_40;

    DecimalFormat dFormat = new DecimalFormat(":00");
    double playTime_sec;
    int playTime_min;

    public UI(Game gp) {
        this.gp = gp;
        this.Comic_Sans_40 = new Font("Comic-Sans", Font.PLAIN, 40);
    }

    public void draw(Graphics2D g2D) {
        g2D.setFont(Comic_Sans_40);
        g2D.setColor(Color.BLUE);
        g2D.drawString("Speed = " + gp.player.speed, 50, 50);


        playTime_sec += (double) 1 / 60;
        if (playTime_sec >= (double) 59) {
            playTime_min++;
            playTime_sec = (double) 0;
        }
        g2D.drawString("" + playTime_min + dFormat.format(playTime_sec), gp.getScreenWidth() / 2 - 10, 50);

    }
}
